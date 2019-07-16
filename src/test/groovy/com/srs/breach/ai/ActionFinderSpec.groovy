package com.srs.breach.ai

import com.srs.breach.game.Game
import com.srs.breach.game.Mission
import com.srs.breach.game.Weapon
import com.srs.breach.game.board.BitBoard
import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Point
import com.srs.breach.game.entity.Mech
import spock.lang.Specification

class ActionFinderSpec extends Specification {

  def actionFinder = new ActionFinder()

  def board = new Board()
  def game = new Game(
    mission: new Mission(
      board: board
    )
  )

  def 'Find actions'() {
    def mech = new Mech(
      location: new Point(3, 3),
      canMove: true,
      canAct: true,
      moveSpeed: 2,
      weapons: [
        new Weapon(type: Weapon.Type.Repair),
        new Weapon(type: Weapon.Type.TitanFist),
        new Weapon(type: Weapon.Type.TaurusCannon),
        new Weapon(type: Weapon.Type.ArtemisArtillery)
      ]
    )
    board.place(mech)

    when:
    def actions = actionFinder.find(mech, game)

    then:
    BitBoard.of(actions.findAll { action ->
      action.type == Action.Type.Move
    }.target) == BitBoard.distance(mech.location.x, mech.location.y, 2)

    BitBoard.of(actions.findAll { action ->
      action.type == Action.Type.Weapon && action.weapon.type == Weapon.Type.Repair
    }.target) == BitBoard.of(mech.location)

    BitBoard.of(actions.findAll { action ->
      action.type == Action.Type.Weapon && action.weapon.type == Weapon.Type.TitanFist
    }.target) == BitBoard.adjacent(mech.location.x, mech.location.y)

    BitBoard.of(actions.findAll { action ->
      action.type == Action.Type.Weapon && action.weapon.type == Weapon.Type.TaurusCannon
    }.target) == BitBoard.adjacent(mech.location.x, mech.location.y)

    BitBoard.of(actions.findAll { action ->
      action.type == Action.Type.Weapon && action.weapon.type == Weapon.Type.ArtemisArtillery
    }.target) == BitBoard.from('''
      . . . X . . . .
      . . . X . . . .
      . . . . . . . .
      X X . . . X X X
      . . . . . . . .
      . . . X . . . .
      . . . X . . . .
      . . . X . . . .
    ''')
  }

}
