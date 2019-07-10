package com.srs.breach.game.entity

import com.srs.breach.game.board.Point

import static com.srs.breach.game.entity.Entity.*
import static com.srs.breach.game.entity.Entity.Ability.*

class Npc extends ActionEntity {

  Type type

  @Override
  List<Point> getExtraLocations() {
    switch (type) {
      case Type.Train: return [location.south()]
      case Type.Dam: return [location.east()]

      default: []
    }
  }

  enum Type {

    Train,
    Dam

    // todo: fill these in...
    ;

    int health
    int move
    List<Ability> abilities

    Type() {
    }

    Type(int health, int move, Ability... abilities) {
      this.health = health
      this.move = move
      this.abilities = abilities.toList() + Massive  // all mech are massive
    }

  }

}
