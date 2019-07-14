package com.srs.breach.game.entity

import com.srs.breach.game.Weapon

trait Actor implements Entity, Mobile {

  boolean canAct = true
  int order

  List<Weapon> weapons = []

}
