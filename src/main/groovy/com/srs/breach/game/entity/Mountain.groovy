package com.srs.breach.game.entity

class Mountain extends AbstractEntity {

  Mountain() {
    this.health = 2
    this.maxHealth = 2
  }

  boolean isDamaged() {
    this.health == 1
  }

  static Mountain create() {
    new Mountain()
  }

  static Mountain createDamaged() {
    new Mountain(health: 1)
  }

}
