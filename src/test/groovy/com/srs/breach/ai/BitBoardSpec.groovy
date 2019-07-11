package com.srs.breach.ai

import com.srs.breach.game.board.Point
import spock.lang.Specification
import spock.lang.Unroll

class BitBoardSpec extends Specification {

  def 'Set and get'() {
    when:
    def board = new BitBoard()
    board.set(1, 3, true)

    board.set(5, 7, true)
    board.set(5, 7, false)

    then:
    board.count() == 1

    board.get(1, 3)
    !board.get(3, 5)

    board.bits == (1L << (1 + (3 * 8)))

    !board.get(0, 3)
    !board.get(2, 3)
    !board.get(1, 2)
    !board.get(1, 4)
  }

  def 'Parse board'() {
    def text = normalize('''
      . . . . . . . .
      . . . . . . . .
      . . . X . . . .
      . . X . X . . .
      . . . X . . . .
      . . . . . . . .
      . . . . . . . .
      . . . . . . . .
    ''')

    when:
    def board = BitBoard.from(text)

    then:
    board.toString() == text

    board.count() == 4

    board.get(3, 2)
    board.get(2, 3)
    board.get(4, 3)
    board.get(3, 4)
  }

  def 'Convert to points'() {
    def points = [
      new Point(3, 2),
      new Point(2, 3),
      new Point(4, 3),
      new Point(3, 4)
    ]

    when:
    def board = BitBoard.from(points)

    then:
    board.count() == 4
    board.toPoints() == points
  }

  def 'Shift board'() {
    def board = BitBoard.from('''
      . X .
      X X .
      . . .
    ''')

    when:
    def shifted = board.shift(1, 1)

    then:
    shifted == BitBoard.from('''
      . . .
      . . X
      . X X
    ''')
  }

  def 'Union'() {
    def first = BitBoard.from('''
      . X .
      X X .
      . . .
    ''')

    def second = BitBoard.from('''
      . . .
      . X X
      . X .
    ''')

    when:
    def union = first.union(second)

    then:
    union == BitBoard.from('''
      . X .
      X X X
      . X .
    ''')
  }

  def 'Intersection'() {
    def first = BitBoard.from('''
      . X .
      X X .
      . . .
    ''')

    def second = BitBoard.from('''
      . . .
      . X X
      . X .
    ''')

    when:
    def intersection = first.intersect(second)

    then:
    intersection == BitBoard.from('''
      . . .
      . X .
      . . .
    ''')
  }

  def 'Shift board - negative'() {
    def board = BitBoard.from('''
      . . .
      . . X
      . X X
    ''')

    when:
    def shifted = board.shift(-1, -1)

    then:
    shifted == BitBoard.from('''
      . X .
      X X .
      . . .
    ''')
  }

  def 'Shift board - partially out of bounds'() {
    def board = BitBoard.from('''
      X X
      X .
    ''')

    when:
    def shifted = board.shift(7, 7)

    then:
    shifted == BitBoard.from('''
      . . . . . . . .
      . . . . . . . .
      . . . . . . . .
      . . . . . . . .
      . . . . . . . .
      . . . . . . . .
      . . . . . . . .
      . . . . . . . X
    ''')
  }

  def 'Shift board - negative partially out of bounds'() {
    def board = BitBoard.from('''
      . X
      X X
    ''')

    when:
    def shifted = board.shift(-1, -1)

    then:
    shifted == BitBoard.from('''
      X .
      . .
    ''')
  }

  @Unroll
  def 'Shift board - fully out of bounds - #offset'() {
    def board = BitBoard.full()

    when:
    def shifted = board.shift(offset, offset)

    then:
    shifted == BitBoard.empty()
    shifted.count() == 0

    where:
    //noinspection GroovyAssignabilityCheck
    offset << [8, -8, 100, -100, Integer.MAX_VALUE, Integer.MIN_VALUE]
  }

  private String normalize(String text) {
    text.stripIndent().readLines()*.trim().findAll().join('\n')
  }

}
