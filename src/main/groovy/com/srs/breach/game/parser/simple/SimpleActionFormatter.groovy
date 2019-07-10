package com.srs.breach.game.parser.simple

import com.srs.breach.game.Action
import com.srs.breach.game.board.Board

import static com.srs.breach.game.Action.Type.*
import static com.srs.breach.game.parser.simple.SimpleBreachNotation.*

class SimpleActionFormatter {

  String format(Action action, Board board) {

    def mech = toSymbol(action.mech)
    def target = action.target

    def targetEntity = toSymbol(board.get(target).entity)

    switch (action.type) {
      case Move: return "$mech -> $target (move)"
      case Repair: return "$mech++ ${target != action.mech.location ? target : ''} (repair)"
      case PrimaryWeapon: return "$mech >1> $target $targetEntity (primary)"
      case SecondaryWeapon: return "$mech >2> $target $targetEntity (secondary)"

      default: throw new IllegalArgumentException("Unknown action type [$action.type]")
    }
  }

}
