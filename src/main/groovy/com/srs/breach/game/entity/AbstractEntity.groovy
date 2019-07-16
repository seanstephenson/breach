package com.srs.breach.game.entity

import com.srs.breach.game.board.Point

abstract class AbstractEntity implements Entity {

  Point location
  int health, healthMax

  List<Effect> effects = []
  List<Ability> abilities = []

  void init() {
  }

  @Override
  List<Point> getExtraLocations() {
    []
  }

  @Override
  String toString() {
    getClass().simpleName + location
  }

}
