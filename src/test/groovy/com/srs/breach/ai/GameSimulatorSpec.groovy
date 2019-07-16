package com.srs.breach.ai

import com.srs.breach.game.Game
import com.srs.breach.game.parser.lua.LuaSaveFileParser
import spock.lang.Specification

class GameSimulatorSpec extends Specification {

  Game game
  GameSimulator simulator

  def setup() {
    def text = GameSimulatorSpec.getResourceAsStream('/saveData.lua').text

    game = new LuaSaveFileParser().parse(text)
    simulator = new GameSimulator(game)
  }

  def 'Do move action'() {

  }

}
