package com.srs.breach.ai

import com.srs.breach.game.board.BitBoard
import com.srs.breach.game.board.Point
import spock.lang.Specification
import spock.lang.Unroll

class BitBoardSpec extends Specification {

  def 'Set and get'() {
    def board = new BitBoard()

    when:
    board.set(1, 3)

    board.set(5, 7)
    board.clear(5, 7)

    then:
    board.count() == 1

    board.get(1, 3)
    !board.get(3, 5)

    board.bits == (1L << (1 + (3 * 8)))

    !board.get(0, 3)
    !board.get(2, 3)
    !board.get(1, 2)
    !board.get(1, 4)

    !board.get(100, 0)
    !board.get(0, 100)

    !board.get(-100, 0)
    !board.get(0, -100)
  }

  @Unroll
  def 'Set and get out of bounds - #x, #y'() {
    def board = new BitBoard()

    when:
    board.set(x, y)

    then:
    board.empty
    !board.get(x, y)

    where:
     x   |  y
    -100 |  0
     100 |  0
     0   | -100
     0   |  100
  }

  def 'Copy'() {
    def board = BitBoard.of(1, 1)

    when:
    def copy = board.copy()
    copy.set(0, 0)

    then:
    board == BitBoard.from('''
      . .
      . X
    ''')
    copy == BitBoard.from('''
      X .
      . X
    ''')
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
    def board = BitBoard.of(points)

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

  def 'Set row'() {
    def board = new BitBoard()

    when:
    board.setRow(1)
    
    then:
    board == BitBoard.from('''
      . . . . . . . .
      X X X X X X X X
    ''')
  }

  def 'Clear row'() {
    def board = BitBoard.full()

    when:
    board.clearRow(1)

    then:
    board == BitBoard.from('''
      X X X X X X X X
      . . . . . . . .
      X X X X X X X X
      X X X X X X X X
      X X X X X X X X
      X X X X X X X X
      X X X X X X X X
      X X X X X X X X
    ''')
  }

  @Unroll
  def 'Set row - out of bounds - #y'() {
    def board = new BitBoard()

    when:
    board.setRow(y)

    then:
    board == BitBoard.empty()

    where:
    y << [-1, -8, -100, 8, 100]
  }

  def 'Set column'() {
    def board = new BitBoard()

    when:
    board.setColumn(1)
    
    then:
    board == BitBoard.from('''
      . X . . . . . .
      . X . . . . . .
      . X . . . . . .
      . X . . . . . .
      . X . . . . . .
      . X . . . . . .
      . X . . . . . .
      . X . . . . . .
    ''')
  }

  def 'Clear column'() {
    def board = BitBoard.full()

    when:
    board.clearColumn(1)

    then:
    board == BitBoard.from('''
      X . X X X X X X
      X . X X X X X X
      X . X X X X X X
      X . X X X X X X
      X . X X X X X X
      X . X X X X X X
      X . X X X X X X
      X . X X X X X X
    ''')
  }

  @Unroll
  def 'Set column - out of bounds - #x'() {
    def board = new BitBoard()

    when:
    board.setColumn(x)

    then:
    board == BitBoard.empty()

    where:
    x << [-1, -8, -100, 8, 100]
  }

  def 'Set adjacent'() {
    def board = new BitBoard()

    when:
    board.setAdjacent(1, 2)

    then:
    board == BitBoard.from('''
      . . . .
      . X . .
      X . X .
      . X . .
    ''')
  }

  def 'Set adjacent - corner'() {
    def board = new BitBoard()

    when:
    board.setAdjacent(0, 0)

    then:
    board == BitBoard.from('''
      . X
      X .
    ''')
  }

  def 'Set adjacent - out of bounds - x'() {
    def board = new BitBoard()

    when:
    board.setAdjacent(-1, 1)

    then:
    board == BitBoard.from('''
      . .
      X .
    ''')
  }

  def 'Set adjacent - out of bounds - y'() {
    def board = new BitBoard()

    when:
    board.setAdjacent(1, -1)

    then:
    board == BitBoard.from('''
      . X
      . .
    ''')
  }

  def 'Clear adjacent'() {
    def board = BitBoard.full()

    when:
    board.clearAdjacent(1, 2)

    then:
    board == BitBoard.from('''
      X X X X X X X X
      X . X X X X X X
      . X . X X X X X
      X . X X X X X X
      X X X X X X X X
      X X X X X X X X
      X X X X X X X X
      X X X X X X X X
    ''')
  }

  def 'Set aligned'() {
    def board = new BitBoard()

    when:
    board.setAlignedWith(1, 2)

    then:
    board == BitBoard.from('''
      . X . . . . . .
      . X . . . . . .
      X . X X X X X X
      . X . . . . . .
      . X . . . . . .
      . X . . . . . .
      . X . . . . . .
      . X . . . . . .
    ''')
  }

  def 'Set aligned - corner'() {
    def board = new BitBoard()

    when:
    board.setAlignedWith(0, 0)

    then:
    board == BitBoard.from('''
      . X X X X X X X
      X . . . . . . .
      X . . . . . . .
      X . . . . . . .
      X . . . . . . .
      X . . . . . . .
      X . . . . . . .
      X . . . . . . .
    ''')
  }

  def 'Set aligned - out of bounds - x'() {
    def board = new BitBoard()

    when:
    board.setAlignedWith(-1, 1)

    then:
    board == BitBoard.from('''
      . . . . . . . .
      X X X X X X X X
    ''')
  }

  def 'Set aligned - out of bounds - y'() {
    def board = new BitBoard()

    when:
    board.setAlignedWith(1, -1)

    then:
    board == BitBoard.from('''
      . X
      . X
      . X
      . X
      . X
      . X
      . X
      . X
    ''')
  }

  def 'Clear aligned'() {
    def board = BitBoard.full()

    when:
    board.clearAlignedWith(1, 2)

    then:
    board == BitBoard.from('''
      X . X X X X X X
      X . X X X X X X
      . X . . . . . .
      X . X X X X X X
      X . X X X X X X
      X . X X X X X X
      X . X X X X X X
      X . X X X X X X
    ''')
  }

  def 'Set distance'() {
    def board = new BitBoard()

    when:
    board.setDistance(3, 3, 2)

    then:
    board == BitBoard.from('''
      . . . . . . . .
      . . . X . . . .
      . . X X X . . .
      . X X . X X . .
      . . X X X . . .
      . . . X . . . .
      . . . . . . . .
      . . . . . . . .
    ''')
  }

  def 'Set distance - partially out of bounds'() {
    def board = new BitBoard()

    when:
    board.setDistance(8, 3, 2)

    then:
    board == BitBoard.from('''
      . . . . . . . .
      . . . . . . . .
      . . . . . . . X
      . . . . . . X X
      . . . . . . . X
      . . . . . . . .
      . . . . . . . .
      . . . . . . . .
    ''')
  }

  def 'Set distance - out of bounds'() {
    def board = new BitBoard()

    when:
    board.setDistance(100, 100, 70)

    then:
    board == BitBoard.empty()
  }


  def 'Clear distance'() {
    def board = BitBoard.full()

    when:
    board.clearDistance(3, 3, 2)

    then:
    board == BitBoard.from('''
      X X X X X X X X
      X X X . X X X X
      X X . . . X X X
      X . . X . . X X
      X X . . . X X X
      X X X . X X X X
      X X X X X X X X
      X X X X X X X X
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
