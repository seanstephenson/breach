package com.srs.breach.game.board

import com.srs.breach.game.entity.Entity
import com.srs.breach.game.entity.TerrainEntity

import static com.srs.breach.game.board.Tile.Effect.*

class Tile {

  Entity entity
  Terrain terrain = Terrain.Normal
  Effect effect = None
  boolean spawnPoint

  enum Effect {
    None,
    Fire,
    Acid,
    Smoke
  }

  Tile() {
  }

  Tile(Entity entity) {
    this.entity = entity

    if (entity instanceof TerrainEntity) {
      this.terrain = entity.terrain
    }
  }

  Tile(Terrain terrain) {
    this.terrain = terrain
  }

}
