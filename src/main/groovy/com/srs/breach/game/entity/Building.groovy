package com.srs.breach.game.entity

import com.srs.breach.game.board.Terrain

class Building extends TerrainEntity {

  @Override
  Terrain getTerrain() {
    health == 1 ?
      Terrain.BuildingSingle :
      Terrain.BuildingDouble
  }

  static Building doubleBuilding() {
    new Building(health: 2, healthMax: 2)
  }

  static Building singleBuilding() {
    new Building(health: 1, healthMax: 1)
  }

}
