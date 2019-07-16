package com.srs.breach.game.entity

import com.srs.breach.game.Weapon

abstract class Actor extends Mobile {

  boolean canAct
  boolean acted
  int order

  List<Weapon> weapons = []

  @Override
  void init() {
    super.init()

    canAct = !acted
  }

}
