package com.srs.breach.game.board

import com.srs.breach.game.entity.Mech
import com.srs.breach.game.entity.Mountain
import com.srs.breach.game.entity.Team
import spock.lang.Specification
import spock.lang.Unroll

class BoardSpec extends Specification {

  def board = new Board()

  def 'Place entity'() {
    def mountain = Mountain.create()
    def mech = new Mech()

    when:
    board.place(mountain, 1, 1)
    board.place(mech, 0, 0)

    then:
    board.get(1, 1).entity == mountain
    mountain.location == new Point(1, 1)
    board.get(0, 0).entity == mech
    mech.location == new Point(0, 0)

    board.contains(mountain)
    board.contains(mech)

    board.occupied == BitBoard.from('''
      X .
      . X
    ''')

    board.occupied(Team.Player) == BitBoard.from('''
      X .
      . .
    ''')

    board.terrain == BitBoard.from('''
      . .
      . X
    ''')

    board.entities.size() == 2
    board.entities.containsAll([mountain, mech])

    board.entities(Team.Player) == [mech]
    board.entities(Team.Enemy) == []
    board.entities(Team.Neutral) == []
    board.entities(Team.Terrain) == [mountain]
  }

  def 'Move entity'() {
    def mech = new Mech()
    board.place(mech, 0, 0)

    when:
    board.move(mech, 1, 1)

    then:
    board.occupied == BitBoard.from('''
      . .
      . X
    ''')

    board.occupied(Team.Player) == BitBoard.from('''
      . .
      . X
    ''')

    board.entities == [mech]
  }

  def 'Place over existing entity'() {
    def mech = new Mech()
    def mountain = Mountain.create()

    board.place(mech, 1, 1)

    when:
    board.place(mountain, 1, 1)

    then:
    def error = thrown(AssertionError)
    error.message.startsWith('Tried to place entity [Mountain(1,1)] over existing entity [Mech(1,1)] at [(1,1)]')
  }

  def 'Set over existing entity'() {
    def mech = new Mech()
    def mountain = Mountain.create()

    board.place(mech, 1, 1)

    when:
    board.set(1, 1, new Tile(mountain))

    then:
    board.get(1, 1).entity == mountain
    mountain.location == new Point(1, 1)

    board.contains(mountain)
    !board.contains(mech)

    board.occupied == BitBoard.from('''
      . .
      . X
    ''')

    board.terrain == BitBoard.from('''
      . .
      . X
    ''')

    board.occupied(Team.Player) == BitBoard.from('''
      . .
      . .
    ''')

    board.entities == [mountain]
    board.entities(Team.Player) == []
    board.entities(Team.Terrain) == [mountain]
  }

  def 'Remove entity'() {
    def mech = new Mech()
    board.place(mech, 1, 1)

    when:
    board.remove(mech)

    then:
    !board.get(1, 1).entity

    board.occupied.empty
    board.occupied(Team.Player).empty
    board.entities.empty
    board.entities(Team.Player).empty
  }

  @Unroll
  def 'Get - out of bounds - (#x,#y)'() {
    when:
    board.get(x, y)

    then:
    def error = thrown(AssertionError)
    error.message.startsWith("Out of bounds ($x,$y)")

    where:
     x |  y
    -1 |  0
     0 | -1
     8 |  0
     0 |  8
  }

  @Unroll
  def 'Set - out of bounds - (#x,#y)'() {
    when:
    board.set(x, y, new Tile())

    then:
    def error = thrown(AssertionError)
    error.message.startsWith("Out of bounds ($x,$y)")

    where:
     x |  y
    -1 |  0
     0 | -1
     8 |  0
     0 |  8
  }

}
