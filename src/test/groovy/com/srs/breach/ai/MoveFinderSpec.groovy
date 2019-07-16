package com.srs.breach.ai

import com.srs.breach.game.board.BitBoard
import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Point
import com.srs.breach.game.entity.Enemy
import com.srs.breach.game.entity.Mobile
import com.srs.breach.game.entity.Mountain
import com.srs.breach.game.entity.Team
import spock.lang.Specification

class MoveFinderSpec extends Specification {

  def moveFinder = new MoveFinder()

  static class Mover extends Mobile {

    Team team = Team.Player

    Mover() {
      canMove = true
    }
  }

  def mover = new Mover(moveSpeed: 2, location: new Point(3, 3))
  def board = new Board(mover)

  def 'canMove is false'() {

    mover.canMove = false

    when:
    def moves = moveFinder.find(mover, board)

    then:
    moves.empty
  }

  def 'No obstacles - move speed 2'() {
    when:
    def moves = moveFinder.find(mover, board)

    then:
    moves == BitBoard.from('''
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

  def 'No obstacles - move speed 3'() {

    mover.moveSpeed = 3

    when:
    def moves = moveFinder.find(mover, board)

    then:
    moves == BitBoard.from('''
      . . . X . . . .
      . . X X X . . .
      . X X X X X . .
      X X X . X X X .
      . X X X X X . .
      . . X X X . . .
      . . . X . . . .
      . . . . . . . .
    ''')
  }

  def 'No obstacles - partially out of bounds - southeast'() {

    board.move(mover, new Point(6, 6))

    when:
    def moves = moveFinder.find(mover, board)

    then:
    moves == BitBoard.from('''
      . . . . . . . . 
      . . . . . . . . 
      . . . . . . . . 
      . . . . . . . . 
      . . . . . . X . 
      . . . . . X X X 
      . . . . X X . X 
      . . . . . X X X 
    ''')
  }

  def 'No obstacles - partially out of bounds - northwest'() {

    board.move(mover, new Point(1, 1))

    when:
    def moves = moveFinder.find(mover, board)

    then:
    moves == BitBoard.from('''
      X X X . . . . .
      X . X X . . . .
      X X X . . . . .
      . X . . . . . .
      . . . . . . . .
      . . . . . . . .
      . . . . . . . .
      . . . . . . . .
    ''')
  }

  def 'Obstacles - not flying'() {

    mover.moveSpeed = 3
    board.place(new Mountain(location: mover.location.west()))
    board.place(new Mountain(location: mover.location.east().east()))
    board.place(new Mountain(location: mover.location.north().east()))
    board.place(new Enemy(location: mover.location.north().north().north()))

    /*
      . . . 1 . . . .
      . . . X X . . .
      . X X X M . . .
      . . M ? X M . .
      . X X X X X . .
      . . X X X . . .
      . . . X . . . .
      . . . . . . . .
    */

    when:
    def moves = moveFinder.find(mover, board)

    then:
    moves == BitBoard.from('''
      . . . . . . . .
      . . X X X . . .
      . X X X . . . .
      . . . . X . . .
      . X X X X X . .
      . . X X X . . .
      . . . X . . . .
      . . . . . . . .
    ''')
  }

  def 'Obstacles - flying'() {

    mover.moveSpeed = 3
    mover.flying = true

    board.place(new Mountain(location: mover.location.west()))
    board.place(new Mountain(location: mover.location.east().east()))
    board.place(new Mountain(location: mover.location.north().east()))
    board.place(new Enemy(location: mover.location.north().north().north()))

    /*
      . . . 1 . . . .
      . . X X X . . .
      . X X X M X . .
      X X M ? X M X .
      . X X X X X . .
      . . X X X . . .
      . . . X . . . .
      . . . . . . . .
    */

    when:
    def moves = moveFinder.find(mover, board)

    then:
    moves == BitBoard.from('''
      . . . . . . . .
      . . X X X . . .
      . X X X . X . .
      X X . . X . X .
      . X X X X X . .
      . . X X X . . .
      . . . X . . . .
      . . . . . . . .
    ''')
  }

}
