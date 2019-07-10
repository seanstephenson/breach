package com.srs.breach.game

import com.srs.breach.game.board.Point
import com.srs.breach.game.entity.Mech

class Action {

  Mech mech
  Type type
  Point target

  enum Type {
    Move,
    Repair,
    PrimaryWeapon,
    SecondaryWeapon
  }

}
