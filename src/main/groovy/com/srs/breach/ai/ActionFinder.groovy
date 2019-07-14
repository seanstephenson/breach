package com.srs.breach.ai

import com.srs.breach.game.Game
import com.srs.breach.game.Weapon
import com.srs.breach.game.board.BitBoard
import com.srs.breach.game.entity.Actor

import static com.srs.breach.game.Weapon.AimType.*

class ActionFinder {

  def moveFinder = new MoveFinder()

  List<Action> find(Actor actor, Game game) {

    def actions = []

    if (actor.canMove) {
      findMoveActions(actor, game, actions)
    }

    if (actor.canAct) {
      for (weapon in actor.weapons) {
        findWeaponActions(actor, weapon, actions)
      }
    }

    actions
  }

  private void findMoveActions(Actor actor, Game game, List<Action> actions) {

    def moves = moveFinder.find(actor, game.mission.board)

    actions.addAll(
      moves.toPoints().collect { location ->
        new Action(actor: actor, type: Action.Type.Move, target: location)
      }
    )
  }

  private void findWeaponActions(Actor actor, Weapon weapon, List<Action> actions) {

    def targets = findWeaponTargets(actor, weapon)

    actions.addAll(
      targets.collect { target ->
        new Action(actor: actor, type: Action.Type.Weapon, target: target, weapon: weapon)
      }
    )
  }

  private BitBoard findWeaponTargets(Actor actor, Weapon weapon) {

    def origin = actor.location
    def aimType = weapon.type.aimType

    if (aimType == Passive) {
      BitBoard.empty()

    } else if (aimType == Self) {
      BitBoard.of(origin)

    } else {
      def rangeMin = weapon.type.rangeMin
      def rangeMax = weapon.type.rangeMax + weapon.extraRange

      BitBoard board
      if (rangeMax == 1) {
        board = BitBoard.adjacent(origin.x, origin.y)

      } else {
        board = BitBoard.alignedWith(origin.x, origin.y)
      }

      if (rangeMin > 1) {
        board.clearDistance(origin.x, origin.y, rangeMin - 1)
      }

      board
    }
  }

}
