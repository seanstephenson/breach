package com.srs.breach.game.entity

import com.srs.breach.game.board.Terrain

class Mountain extends TerrainEntity {

  Mountain() {
    this.health = 2
    this.healthMax = 2
  }

  boolean isDamaged() {
    this.health == 1
  }

  @Override
  Terrain getTerrain() {
    isDamaged() ?
      Terrain.MountainDamaged :
      Terrain.Mountain
  }

  static Mountain create() {
    new Mountain()
  }

  static Mountain createDamaged() {
    new Mountain(health: 1)
  }

}
