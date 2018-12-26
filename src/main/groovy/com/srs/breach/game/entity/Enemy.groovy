package com.srs.breach.game.entity

class Enemy implements Entity {

  Type type
  int order

  int hp
  int hpMax

  boolean alpha

  enum Type {
    Hornet
  }

}
