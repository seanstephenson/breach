package com.srs.breach.ai

import com.srs.breach.game.Weapon
import com.srs.breach.game.board.Point
import com.srs.breach.game.entity.Actor
import com.srs.breach.game.parser.simple.SimpleBreachNotation

class Action {

  Actor actor
  Type type
  Point target
  Weapon weapon

  enum Type {
    None,
    Move,
    Weapon
  }

  @Override
  String toString() {
    SimpleBreachNotation.format(this)
  }

}
