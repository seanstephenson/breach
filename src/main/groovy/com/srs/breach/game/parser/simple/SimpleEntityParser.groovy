package com.srs.breach.game.parser.simple

import com.srs.breach.game.Pilot
import com.srs.breach.game.entity.Enemy
import com.srs.breach.game.entity.Entity
import com.srs.breach.game.entity.Mech

/**
 * Parses entities (mech, enemy, npc) from simple breach format.
 */
class SimpleEntityParser {

  Map<String, Entity> parse(String text) {

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

}
