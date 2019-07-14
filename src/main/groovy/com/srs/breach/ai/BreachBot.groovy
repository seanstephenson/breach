package com.srs.breach.ai

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.github.difflib.DiffUtils
import com.srs.breach.game.parser.lua.LuaSaveFileParser
import com.srs.breach.game.parser.simple.SimpleBreachNotation
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds
import java.nio.file.WatchEvent

class BreachBot {

  AutoPlayer player

  def parser = new LuaSaveFileParser()

  boolean printDiff = true
  private String previousText

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

    println()
    println SimpleBreachNotation.format(game.mission.board)

    def actions = player.getTurnActions(game)

    println()
    println('Actions:')
    actions.each { action ->
      println SimpleBreachNotation.format(action)
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
        println delta
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

  void watchFile(Path file, Closure onChange) {

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
