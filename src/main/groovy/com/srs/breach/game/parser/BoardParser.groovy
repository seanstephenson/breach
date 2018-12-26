package com.srs.breach.game.parser

import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Terrain
import com.srs.breach.game.board.Tile
import com.srs.breach.game.entity.Building
import com.srs.breach.game.entity.Entity
import com.srs.breach.game.entity.Mountain

import static com.srs.breach.game.board.Terrain.*
import static com.srs.breach.game.board.Tile.Effect.*

class BoardParser {

  Board parse(String text) {

    // Trim all lines
    text = text.readLines()*.trim().join('\n')

    // Split into sections
    def sections = text.split('\n\n')

    // There should be two sections: the entities and then the tiles
    assert sections.size() == 2

    def entities = parseEntities(sections.first())
    def tiles = parseTiles(sections.last(), entities)

    new Board(tiles)
  }

  Map<String, Entity> parseEntities(String text) {

    [:]
  }

  List<List<Tile>> parseTiles(String text, Map<String, Entity> entities) {

    def lines = text.readLines().findAll()

    lines.collect { line ->
      line.tokenize().collect { token ->
        parseTile(token, entities)
      }
    }.reverse()
  }

  Tile parseTile(String text, Map<String, Entity> entities) {

    def terrainTypes = [
      '.': Normal,
      '~': Water,
      '#': Lava,
      '_': Chasm,
      '-': Ice,
      '=': IceDamaged,
      '!': Forest,
      '*': Sand
    ]

    def specialEntities = [
      'M': new Mountain(),
      'm': new Mountain(damaged: true),
      'B': new Building(health: 2),
      'b': new Building(health: 1)
    ]

    def effects = [
      'f': Fire,
      'a': Acid,
      's': Smoke,
    ]

    def spawnPointSymbol = '^'

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
      if (c == spawnPointSymbol) {
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

      } else if (c in effects.keySet()) {
        if (effect) {
          throw new IllegalArgumentException("Multiple effect indicators [$c] found in tile [$text]")
        }
        effect = effects[c]

      } else if (c in terrainTypes.keySet()) {
        if (terrain) {
          throw new IllegalArgumentException("Multiple terrain type indicators [$c] found in tile [$text]")
        }
        terrain = terrainTypes[c]

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
