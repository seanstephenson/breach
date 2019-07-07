package com.srs.breach.game.entity

abstract class AbstractEntity implements Entity {

  int x, y
  int health, maxHealth

  Effect effect
  List<Ability> abilities

  AbstractEntity() {
  }

  @Override
  void setLocation(int x, int y) {
    this.x = x
    this.y = y
  }

}
