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

  @Override
  void init() {

    abilities = type.abilities

    initHealth()
    initMoveSpeed()
    initWeapons()

    super.init()
  }

  private void initWeapons() {
    weapons = []

    weapons << new Weapon(type: Weapon.Type.Repair)

    if (equipment.primaryWeapon?.powered) {
      weapons << equipment.primaryWeapon
    }
    if (equipment.secondaryWeapon?.powered) {
      weapons << equipment.secondaryWeapon
    }

    weapons.each { weapon -> weapon.init() }
  }

  private void initMoveSpeed() {
    moveSpeed = type.move
    if (Pilot.Skill.Move in pilot.skills) {
      moveSpeed += 1
    }
    if (equipment.moveUpgrade) {
      moveSpeed += 1
    }
  }

  private void initHealth() {
    healthMax = type.health
    if (Pilot.Skill.Health in pilot.skills) {
      healthMax += 2
    }
    if (equipment.healthUpgrade) {
      healthMax += 2
    }
  }

  @Override
  String toString() {
    "$type $location"
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
