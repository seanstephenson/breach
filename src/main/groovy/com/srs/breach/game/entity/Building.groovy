package com.srs.breach.game.entity

class Building implements Entity {

  int health

  static Building doubleBuilding() {
    new Building(health: 2)
  }

  static Building singleBuilding() {
    new Building(health: 1)
  }

}
