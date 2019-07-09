package com.srs.breach.ai


import com.srs.breach.game.parser.lua.LuaSaveFileParser
import com.srs.breach.game.parser.simple.SimpleBoardFormatter
import java.nio.file.Paths

class BreachBot {

  void run() {

    // Load the current save file
    def userHome = Paths.get(System.getProperty('user.home'))
    def profile = userHome.resolve('Library/Application Support/IntoTheBreach/profile_Alpha')

    def saveFile = profile.resolve('saveData.lua')

    def game = new LuaSaveFileParser().parse(saveFile.toFile().text)

    println new SimpleBoardFormatter().format(game.mission.board)

  }


  static void main(String[] args) {
    new BreachBot().run()
  }

}
