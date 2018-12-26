package com.srs.breach.game.entity

import com.srs.breach.game.Pilot
import com.srs.breach.game.Weapon

class Mech implements Entity {

  Type type
  Pilot pilot

  int availablePower
  int bonusPower
  int usedPower
  int totalPower

  boolean healthUpgrade
  boolean moveUpgrade

  Weapon firstWeapon
  Weapon secondWeapon

  enum Type {

    // Rift Walkers
    CombatMech,
    CannonMech,
    ArtilleryMech,

    // Rusting Hulks
    JetMech,
    RocketMech,
    PulseMech,

  }

}
