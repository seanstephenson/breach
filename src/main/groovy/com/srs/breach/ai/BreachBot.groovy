package com.srs.breach.ai

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.github.difflib.DiffUtils
import com.github.difflib.patch.DeltaType
import com.srs.breach.game.parser.lua.LuaSaveFileParser
import com.srs.breach.game.parser.simple.SimpleBreachNotation
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds
import java.nio.file.WatchEvent
import java.time.Duration
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class BreachBot {

  AutoPlayer player

  def parser = new LuaSaveFileParser()

  boolean printDiff = true
  private String previousText

  private ExecutorService executor = Executors.newCachedThreadPool()

  void run() {

    player = new AutoPlayer()

    // Load the current save file
    Path saveFile = findSaveFile()

    // Think immediately, and then again every time the save file changes.
    think()
    watchFile(saveFile) { think() }
  }

  void think() {

    def saveFile = findSaveFile()
    def text = saveFile.toFile().text

    // Check for changes to the file and print out the differences.
    if (printDiff) {
      printDiff(text)
    }

    // Parse the save file and find the best moves.
    def game = parser.parse(text)
    def board = game.mission.board

    println()
    println SimpleBreachNotation.format(board)
    println()

    def turn = callWithProgress(
      {
        player.selectTurn(game)
      } as Callable<Turn>,
      {
        def context = player.searchContext
        println "... ${(context.percentComplete * 100).toInteger() / 100}% (${context.elapsed.toInteger()}s) ${context.actionsEvaluated} actions, ${context.actionsPerSecond.toInteger()} per second"
      },
      Duration.ofSeconds(5)
    )

    println()
    println('Actions:')
    turn.actions.each { action ->
      println SimpleBreachNotation.format(action, board)
    }
  }

  private <T> T callWithProgress(Callable<T> callable, Closure progress, Duration progressInterval) {
    def future = executor.submit(callable)

    while (true) {
      try {
        return future.get(progressInterval.toMillis(), TimeUnit.MILLISECONDS)

      } catch (TimeoutException ignored) {
        // There was no result yet, so call the progress handler then loop and try again
        progress()
      }
    }
  }

  private void printDiff(String text) {

    if (previousText) {
      def before = parser.toMap(previousText)
      def after = parser.toMap(text)

      def objectMapper = new ObjectMapper()
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT)

      def patch = DiffUtils.diff(
        objectMapper.writeValueAsString(before).readLines(),
        objectMapper.writeValueAsString(after).readLines()
      )

      println()
      println("Detected [${patch.deltas.size()}] changes:")

      patch.deltas.each { delta ->
        print "$delta.type:"
        if (delta.type == DeltaType.CHANGE || delta.type == DeltaType.DELETE) {
          print "  Before [${delta.source.lines.join('\n')}]"
        }
        if (delta.type == DeltaType.CHANGE || delta.type == DeltaType.INSERT) {
          print "  After [${delta.target.lines.join('\n')}]"
        }
        println()
      }
    }

    previousText = text
  }

  private Path findSaveFile() {
    def userHome = Paths.get(System.getProperty('user.home'))
    def profile = userHome.resolve('Library/Application Support/IntoTheBreach/profile_Alpha')

    def saveFile = profile.resolve('saveData.lua')

    if (!Files.exists(saveFile)) {
      throw new IllegalArgumentException("Could not find save file at [${saveFile.toString()}]")
    }

    saveFile
  }

  private void watchFile(Path file, Closure onChange) {

    FileSystems.getDefault().newWatchService().withCloseable { watcher ->
      def parent = file.parent
      parent.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY)

      //noinspection GroovyInfiniteLoopStatement
      while (true) {
        def key = watcher.take()

        key.pollEvents().each { WatchEvent<Path> event ->

          def kind = event.kind()

          if (kind == StandardWatchEventKinds.ENTRY_MODIFY && event.context().fileName == file.fileName) {
            onChange(file)
          }
        }

        key.reset()
      }
    }
  }

  static void main(String[] args) {
    new BreachBot().run()
  }

}
