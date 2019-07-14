package com.srs.breach.ai

import com.srs.breach.game.Weapon
import com.srs.breach.game.board.Point
import com.srs.breach.game.entity.Actor

class Action {

  Actor actor
  Type type
  Point target
  Weapon weapon

  enum Type {
    Move,
    Weapon
  }

}
