package com.srs.breach.game.entity

import com.srs.breach.game.Pilot
import com.srs.breach.game.Weapon

import static com.srs.breach.game.entity.Entity.*
import static com.srs.breach.game.entity.Entity.Ability.*

class Mech extends Actor {

  Type type
  Pilot pilot
  Equipment equipment

  @Override
  Team getTeam() {
    Team.Player
  }

  static class Equipment {

    boolean healthUpgrade
    boolean moveUpgrade

    Weapon primaryWeapon
    Weapon secondaryWeapon

  }

  enum Type {

    // Rift Walkers
    CombatMech(3, 3),
    CannonMech(3, 3),
    ArtilleryMech(2, 3),

    // Rusting Hulks
    JetMech(2, 4, Flying),
    RocketMech(3, 3),
    PulseMech(3, 4),

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
