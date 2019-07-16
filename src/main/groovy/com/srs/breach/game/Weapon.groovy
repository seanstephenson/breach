package com.srs.breach.game

import static com.srs.breach.game.Weapon.AimType.*

class Weapon {

  Type type

  boolean powered
  boolean firstModPowered
  boolean secondModPowered

  int extraRange

  void init() {
    initExtraRange()
  }

  void initExtraRange() {
    // todo: implement
  }

  @Override
  String toString() {
    type.name()
  }

  enum Type {

    Repair(Self),

    TitanFist(Melee),
    TaurusCannon(Projectile),
    ArtemisArtillery(Ranged),

    // todo: fill these in...
    ;

    AimType aimType
    int rangeMin, rangeMax

    Type(AimType aimType) {
      this.aimType = aimType

      if (aimType == Melee || aimType == Projectile) {
        (rangeMin, rangeMax) = [1, 1]
      } else if (aimType == Ranged) {
        (rangeMin, rangeMax) = [2, 8]
      }
    }
  }

  enum AimType {

    /**
     * The weapon has a passive effect and can't be aimed or used directly.
     */
    Passive,

    /**
     * The weapon does not have a specific target, or targets the user directly.
     */
    Self,

    /**
     * The weapon can be aimed at a square within some range of the source (e.g. punch, flame thrower, teleport).
     */
    Melee,

    /**
     * The weapon can be aimed in any of the four directions (e.g. projectiles, wind torrent).
     */
    Projectile,

    /**
     * The weapon is ranged, and can be fired in any of the four directions at a specific target 2 or more tiles away.
     */
    Ranged

  }

}
