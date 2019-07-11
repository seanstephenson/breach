package com.srs.breach.game.entity

abstract class Actor extends AbstractEntity {

  int order

  interface Type {

    int getMove()

  }

}
