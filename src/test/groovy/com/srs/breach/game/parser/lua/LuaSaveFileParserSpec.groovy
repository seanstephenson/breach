package com.srs.breach.game.parser.lua


import spock.lang.Specification

class LuaSaveFileParserSpec extends Specification {

  def 'Parse save file'() {
    setup:
    def text = LuaSaveFileParserSpec.getResourceAsStream('/saveData.lua').text

    when:
    def game = new LuaSaveFileParser().parse(text)

    then:
    game
  }

}
