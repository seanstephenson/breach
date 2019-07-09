package com.srs.breach.game.entity

abstract class AbstractEntity implements Entity {

  int x, y
  int health, healthMax

  Effect effect
  List<Ability> abilities

  @Override
  void setLocation(int x, int y) {
    this.x = x
    this.y = y
  }

}
