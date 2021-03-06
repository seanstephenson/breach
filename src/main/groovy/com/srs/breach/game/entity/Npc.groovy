package com.srs.breach.game.entity

import com.srs.breach.game.board.Point

import static com.srs.breach.game.entity.Entity.*
import static com.srs.breach.game.entity.Entity.Ability.*
import static com.srs.breach.game.entity.Npc.Type.*

class Npc extends Actor {

  Type type
  Team team = Team.Neutral

  @Override
  List<Point> getExtraLocations() {
    switch (type) {

      case Train:
      case TrainDamaged: return [location.south()]

      case Dam: return [location.east()]

      default: []
    }
  }

  enum Type {

    Train,
    TrainDamaged,
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
