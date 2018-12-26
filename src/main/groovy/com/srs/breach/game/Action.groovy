package com.srs.breach.game

import com.srs.breach.game.entity.Mech

class Action {

  Mech mech
  Type type
  int x, y

  enum Type {
    Move,
    Repair,
    PrimaryWeapon,
    SecondaryWeapon
  }

}
