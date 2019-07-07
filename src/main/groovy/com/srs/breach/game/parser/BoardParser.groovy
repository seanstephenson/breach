package com.srs.breach.game.parser

import com.srs.breach.game.Pilot
import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Terrain
import com.srs.breach.game.board.Tile
import com.srs.breach.game.entity.Building
import com.srs.breach.game.entity.Enemy
import com.srs.breach.game.entity.Entity
import com.srs.breach.game.entity.Mech
import com.srs.breach.game.entity.Mountain

import static com.srs.breach.game.board.Terrain.*
import static com.srs.breach.game.board.Tile.Effect.*

class BoardParser {

  Board parse(String text) {

    // Trim all lines
    text = text.readLines()*.trim().join('\n')

    // Split into sections
    def sections = text.split('\n\n').toList()
    assert sections.size() <= 2

    def entities = sections.size() > 1 ?
      parseEntities(sections.remove(0)) :
      [:]

    def tiles = parseTiles(sections.remove(0), entities)

    new Board(tiles)
  }

  private Map<String, Entity> parseEntities(String text) {

    def lines = text.readLines().findAll().collect { it.trim() }

    lines.collectEntries { line ->
      parseEntity(line)
    }
  }

  private Map<String, Entity> parseEntity(String text) {

    // [1-9]: <enemy description>
    // [XYZ]: <mech description>

    def key = text[0]
    assert key ==~ /[A-Z0-9]/

    assert text[1] == ':'

    def description = text.substring(2).trim()

    Entity entity
    if (key ==~ /[A-Z]/) {
      entity = parseMechEntity(description)
    } else if (key ==~ /[0-9]/) {
      entity = parseEnemyEntity(description, key.toInteger())
    } else {
      throw new RuntimeException()
    }

    [(key): entity]
  }

  private Mech parseMechEntity(String text) {

    // Jet | 3 hp | RalphKarlsen 75xp [Health, Move] | +Health +Move / AerialBombs -+ / TaurusCannon --

    def parts = text.tokenize('|').collect { it.trim() }
    assert parts.size() == 4

    def type = parseEnum(Mech.Type, parts[0])
    def health = parseHealth(parts[1])
    def pilot = parsePilot(parts[2])
    def equipment = parseEquipment(parts[3])

    new Mech(
      type: type,
      pilot: pilot,
      health: health,
      equipment: equipment
    )
  }

  private Pilot parsePilot(String text) {

    def parts = text.tokenize()

    assert parts
  }

  private Mech.Equipment parseEquipment(String text) {

    def parts = text.tokenize()
  }

  private Enemy parseEnemyEntity(String text, int order) {

    // AlphaHornet | 3 hp | acid
    // Digger | 3 hp | fire

    def parts = text.tokenize('|').collect { it.trim() }
    assert parts.size() == 4

    def type = Enemy.Type.valueOf(parts[0])
    def health = parseHealth(parts[1])
    def effect = parseEnum(Entity.Effect, parts[2])

    new Enemy(
      type: type,
      order: order,
      health: health,
      effect: effect
    )
  }

  private int parseHealth(String text) {

    // 3 hp
    assert text ==~ /\d+ hp/
    text.tokenize()[0].toInteger()
  }

  private <T extends Enum> T parseEnum(Class<T> enumClass, String text) {
    def constants = Arrays.asList(enumClass.enumConstants)
    constants.find { T value ->
      value.name().equalsIgnoreCase(text)
    }
  }

  private List<List<Tile>> parseTiles(String text, Map<String, Entity> entities) {

    def lines = text.readLines().findAll()

    lines.collect { line ->
      line.tokenize().collect { token ->
        parseTile(token, entities)
      } as List<Tile>
    }.reverse()
  }

  private Tile parseTile(String text, Map<String, Entity> entities) {

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
      'M': Mountain.create(),
      'm': Mountain.createDamaged(),
      'B': Building.doubleBuilding(),
      'b': Building.singleBuilding()
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
