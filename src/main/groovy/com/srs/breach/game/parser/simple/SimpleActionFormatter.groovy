package com.srs.breach.game.parser.simple

import com.srs.breach.ai.Action
import com.srs.breach.ai.Direction
import com.srs.breach.game.Weapon
import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Point
import com.srs.breach.game.entity.Actor

class SimpleActionFormatter {

  Board board

  SimpleActionFormatter(Board board) {
    this.board = board
  }

  String format(Action action) {

    def actor = action.actor
    def weapon = action.weapon
    def target = action.target

    switch (action.type) {
      case Action.Type.None: return formatNoneAction(actor)
      case Action.Type.Move: return formatMoveAction(actor, target)
      case Action.Type.Weapon: return formatWeaponAction(actor, weapon, target)

      default: throw new IllegalArgumentException("Unknown action type [$action.type]")
    }
  }

  private String formatNoneAction(Actor actor) {
    "$actor: none"
  }

  private String formatMoveAction(Actor actor, Point target) {
    "$actor: move to -> $target"
  }

  private String formatWeaponAction(Actor actor, Weapon weapon, Point target) {

    def targetEntity = board?.get(target)?.entity
    def direction = Direction.from(actor.location, target)

    if (weapon.type == Weapon.Type.Repair) {
      "$actor: repair"
    } else if (weapon.type.aimType == Weapon.AimType.Self) {
      "$actor: weapon $weapon"
    } else {
      "$actor: weapon $weapon -> fire ${direction ?: ''} to $target${targetEntity ? ", $targetEntity" : ''}"
    }
  }

}
