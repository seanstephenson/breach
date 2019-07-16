package com.srs.breach.game.entity

import static com.srs.breach.game.entity.Entity.Ability.*
import static com.srs.breach.game.entity.Entity.Effect.*

abstract class Mobile extends AbstractEntity {

  boolean canMove
  boolean moved

  int moveSpeed

  boolean flying

  @Override
  void init() {
    super.init()

    canMove = !moved
    if (Webbed in effects || Frozen in effects) {
      canMove = false
    }

    if (Flying in abilities) {
      flying = true
    }
    if (Frozen in effects) {
      flying = false
    }
  }

}
