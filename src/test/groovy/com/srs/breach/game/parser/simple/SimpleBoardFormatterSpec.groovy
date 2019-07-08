package com.srs.breach.game.parser.simple

import spock.lang.Specification

class SimpleBoardFormatterSpec extends Specification {

  def parser = new SimpleBoardParser()
  def formatter = new SimpleBoardFormatter()

  def 'Board is formatted correctly'() {
    setup:
    def text = normalize('''
      .  Mf .
      m  M  ^
    ''')

    def board = parser.parse(text)

    when:
    def actual = formatter.format(board)

    then:
    actual == text
  }

  private String normalize(String text) {
    // Remove blank lines and extra whitespace
    text.readLines()*.trim().findAll().join('\n')
  }

}
