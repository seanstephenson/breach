package com.srs.breach.game.parser.simple

import com.srs.breach.game.Action
import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Terrain
import com.srs.breach.game.entity.Enemy
import com.srs.breach.game.entity.Entity
import com.srs.breach.game.entity.Mech
import com.srs.breach.game.entity.Npc
import com.srs.breach.game.entity.TerrainEntity

import static com.srs.breach.game.board.Terrain.*
import static com.srs.breach.game.board.Tile.*
import static com.srs.breach.game.board.Tile.Effect.*

/**
 * Constants which describe simple breach notation, which is a format used for converting game state to and from
 * a simple text format.
 */
class SimpleBreachNotation {

  private static final TERRAIN = [
    '.': Normal,
    'M': Mountain,
    'm': MountainDamaged,
    'B': BuildingDouble,
    'b': BuildingSingle,
    '~': Water,
    '#': Lava,
    '_': Chasm,
    '-': Ice,
    '=': IceDamaged,
    '!': Forest,
    '*': Sand
  ]

  private static final TERRAIN_TO_SYMBOL = inverse(TERRAIN)

  private static final EFFECT = [
    'f': Fire,
    'a': Acid,
    's': Smoke
  ]

  private static final EFFECT_TO_SYMBOL = inverse(EFFECT)

  private static final NPC = [
    'T': Npc.Type.Train,
    'D': Npc.Type.Dam
  ]

  private static final NPC_TO_SYMBOL = inverse(NPC)

  private static final String SPAWN_POINT_SYMBOL = '^'

  static Board parseBoard(String text) {
    new SimpleBoardParser().parse(text)
  }

  static String format(Board board) {
    new SimpleBoardFormatter().format(board)
  }

  static String format(Action action) {
    new SimpleActionFormatter().format(action)
  }

  static String spawnPointSymbol() {
    SPAWN_POINT_SYMBOL
  }

  static Terrain toTerrain(String symbol) {
    TERRAIN[symbol]
  }

  static String toSymbol(Terrain terrain) {
    TERRAIN_TO_SYMBOL[terrain]
  }

  static Effect toEffect(String symbol) {
    EFFECT[symbol]
  }

  static String toSymbol(Effect effect) {
    EFFECT_TO_SYMBOL[effect]
  }

  static String toSymbol(Entity entity) {
    if (entity instanceof Mech) {
      (('X' as char) + entity.order) as char
    } else if (entity instanceof Enemy) {
      (entity.order + 1).toString()
    } else if (entity instanceof TerrainEntity) {
      toSymbol(entity.terrain)
    } else if (entity instanceof Npc) {
      NPC_TO_SYMBOL[entity.type]
    } else {
      null
    }
  }

  private static <K, V> Map<V, K> inverse(Map<K, V> map) {
    map.collectEntries { key, value -> [(value): key] } as Map<V, K>
  }

}
