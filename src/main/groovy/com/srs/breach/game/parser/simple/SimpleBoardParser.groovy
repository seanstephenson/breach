package com.srs.breach.game.parser.simple


import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Terrain
import com.srs.breach.game.board.Tile
import com.srs.breach.game.entity.Building
import com.srs.breach.game.entity.Entity
import com.srs.breach.game.entity.Mountain as MountainEntity

import static com.srs.breach.game.board.Terrain.*
import static com.srs.breach.game.board.Tile.Effect.*
import static com.srs.breach.game.parser.simple.SimpleBreachNotation.*

/**
 * Parses a board from simple breach notation.
 */
class SimpleBoardParser {

  Board parse(String text, Map<String, Entity> entities = [:]) {

    def tiles = parseTiles(text, entities)
    new Board(tiles)
  }

  private List<List<Tile>> parseTiles(String text, Map<String, Entity> entities) {

    def lines = text.readLines()*.trim().findAll() as List<String>

    lines.collect { line ->
      line.tokenize().collect { token ->
        parseTile(token, entities)
      } as List<Tile>
    }
  }

  private Tile parseTile(String text, Map<String, Entity> entities) {

    def specialEntities = [
      (toSymbol(Mountain)): MountainEntity.create(),
      (toSymbol(MountainDamaged)): MountainEntity.createDamaged(),
      (toSymbol(BuildingDouble)): Building.doubleBuilding(),
      (toSymbol(BuildingSingle)): Building.singleBuilding()
    ]

    Entity entity = null
    Terrain terrain = null
    Tile.Effect effect = null
    boolean spawnPoint = false

    entities.keySet().each { key ->
      if (key in specialEntities.keySet()) {
        throw new IllegalArgumentException("Entity found with reserved symbol [$key]")
      }
    }

    text.toList().each { c ->
      if (c == spawnPointSymbol()) {
        if (spawnPoint) {
          throw new IllegalArgumentException("Multiple spawn point indicators [$c] found in tile [$text]")
        }
        spawnPoint = true

      } else if (c in entities.keySet()) {
        if (entity) {
          throw new IllegalArgumentException("Multiple entity indicators [$c] found in tile [$text]")
        }
        entity = entities[c]

      } else if (c in specialEntities.keySet()) {
        if (entity) {
          throw new IllegalArgumentException("Multiple entity indicators [$c] found in tile [$text]")
        }
        entity = specialEntities[c]
        terrain = toTerrain(c)

      } else if (toEffect(c)) {
        if (effect) {
          throw new IllegalArgumentException("Multiple effect indicators [$c] found in tile [$text]")
        }
        effect = toEffect(c)

      } else if (toTerrain(c)) {
        if (terrain) {
          throw new IllegalArgumentException("Multiple terrain type indicators [$c] found in tile [$text]")
        }
        terrain = toTerrain(c)

      } else {
        throw new IllegalArgumentException("Unrecognized character [$c] found in tile [$text]")
      }
    }

    new Tile(
      entity: entity,
      terrain: terrain ?: Normal,
      effect: effect ?: None,
      spawnPoint: spawnPoint
    )
  }

}
