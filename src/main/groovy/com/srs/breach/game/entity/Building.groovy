package com.srs.breach.game.entity

class Building extends AbstractEntity {

  static Building doubleBuilding() {
    new Building(health: 2, maxHealth: 2)
  }

  static Building singleBuilding() {
    new Building(health: 1, maxHealth: 1)
  }

}
