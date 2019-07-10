package com.srs.breach.ai

import com.srs.breach.game.parser.lua.LuaSaveFileParser
import com.srs.breach.game.parser.simple.SimpleBreachNotation
import java.nio.file.Paths

class BreachBot {

  void run() {

    // Load the current save file
    def userHome = Paths.get(System.getProperty('user.home'))
    def profile = userHome.resolve('Library/Application Support/IntoTheBreach/profile_Alpha')

    def saveFile = profile.resolve('saveData.lua')

    def game = new LuaSaveFileParser().parse(saveFile.toFile().text)

    println SimpleBreachNotation.format(game.mission.board)

    def player = new AutoPlayer()
    def actions = player.getTurnActions(game)

    println()

    actions.each { action ->
      println SimpleBreachNotation.format(action)
    }
  }


  static void main(String[] args) {
    new BreachBot().run()
  }

}
