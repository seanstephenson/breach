package com.srs.breach.game.board

import com.srs.breach.game.entity.Entity

import static com.srs.breach.game.board.Tile.Effect.*

class Tile {

  Entity entity
  Terrain terrain
  Effect effect = None
  boolean spawnPoint

  enum Effect {
    None,
    Fire,
    Acid,
    Smoke
  }

}
