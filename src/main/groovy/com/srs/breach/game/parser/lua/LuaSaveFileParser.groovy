package com.srs.breach.game.parser.lua

import com.srs.breach.game.Game
import com.srs.breach.game.Mission
import com.srs.breach.game.Pilot
import com.srs.breach.game.Weapon
import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Point
import com.srs.breach.game.board.Terrain
import com.srs.breach.game.board.Tile
import com.srs.breach.game.entity.Building
import com.srs.breach.game.entity.Enemy
import com.srs.breach.game.entity.Mech
import com.srs.breach.game.entity.Mountain
import com.srs.breach.game.entity.Npc
import org.luaj.vm2.Globals
import org.luaj.vm2.LuaTable
import org.luaj.vm2.LuaValue
import org.luaj.vm2.lib.jse.JsePlatform

/**
 * Parses the game state from a lua save file.
 */
class LuaSaveFileParser {

  Game parse(String text) {

    def globals = execute(text)
    parseGame(globals)
  }

  Map<String, Object> toMap(String text) {

    def globals = execute(text)

    ['GameData', 'RegionData', 'SquadData', 'GAME'].collectEntries({ key ->
      [key, LuaUtil.toJava(globals.get(key).checktable())]
    })
  }

  private Globals execute(String text) {
    def globals = createGlobals()

    def chunk = globals.load(text)
    chunk.call()

    globals
  }

  private Globals createGlobals() {
    def globals = JsePlatform.standardGlobals()

    def script = LuaSaveFileParser.getResourceAsStream('/lua/saveFileParser.lua').text
    def base = globals.load(script)
    base.call()

    globals
  }

  private Game parseGame(Globals globals) {

    def gameData = globals.get('GameData').checktable()
    def regionData = globals.get('RegionData').checktable()
    def squadData = globals.get('SquadData').checktable()

    def grid = gameData.get('network').checkint()
    def gridMax = gameData.get('networkMax').checkint()

    def reputation = squadData.get('money').checkint()
    def cores = squadData.get('cores').checkint()

    def activeRegion = findActiveRegion(regionData)

    def mechs = parseMechs(activeRegion)
    def enemies = parseEnemies(activeRegion)
    def npcs = parseNpcs(activeRegion)
    def board = parseBoard(activeRegion)

    board.place(mechs)
    board.place(enemies)
    board.place(npcs)

    new Game(
      mechs: mechs,
      grid: grid,
      gridMax: gridMax,
      reputation: reputation,
      cores: cores,
      mission: new Mission(
        board: board,
        enemies: enemies,
        npcs: npcs
      )
    )
  }

  private LuaTable findActiveRegion(LuaTable regionData) {

    def active = regionData.get('iBattleRegion').checkint()
    regionData.get("region${active}").checktable()
  }

  private List<Mech> parseMechs(LuaTable region) {

    def pawns = findPawns(region)
    def mechs = pawns.findAll { pawn ->
      pawn.get('mech').checkboolean()
    }

    mechs.collect { mech ->
      parseMech(mech)
    }
  }

  private Mech parseMech(LuaTable mech) {

    def location = parsePoint(mech.get('location'))
    def health = mech.get('health').checkint()
    def healthMax = mech.get('max_health').checkint()

    def type = parseMechType(mech.get('type').checkjstring())
    def pilot = parsePilot(mech.get('pilot').checktable())
    def order = mech.get('id').checkint()

    def equipment = parseEquipment(mech)

    new Mech(
      location: location,
      health: health,
      healthMax: healthMax,
      type: type,
      pilot: pilot,
      order: order,
      equipment: equipment
    )
  }

  private Mech.Type parseMechType(String type) {

    switch (type) {
      case 'PunchMech': return Mech.Type.CombatMech
      case 'TankMech': return Mech.Type.CannonMech
      case 'ArtiMech': return Mech.Type.ArtilleryMech

      default: throw new IllegalArgumentException("Unknown mech type [$type]")
    }
  }

  private Pilot parsePilot(LuaTable pilot) {

    def type = parsePilotType(pilot.get('id').checkjstring())
    def name = pilot.get('name').checkjstring()
    def experience = pilot.get('exp').checkint()
    def level = pilot.get('level').checkint()

    new Pilot(
      type: type,
      name: name,
      experience: experience,
      level: level,
      skills: []
    )
  }

  private Pilot.Type parsePilotType(String type) {

    switch (type) {
      case 'Pilot_Original': return Pilot.Type.RalphKarlsen

      case 'Pilot_Detritus':
      case 'Pilot_Pinnacle': return Pilot.Type.Starter

      default: throw new IllegalArgumentException("Unknown pilot type [$type]")
    }
  }

  private Mech.Equipment parseEquipment(LuaTable mech) {

    def healthUpgrade = isPowered(mech.get('healthPower').checktable())
    def moveUpgrade = isPowered(mech.get('movePower').checktable())

    def primaryWeapon = parseWeapon(mech, 'primary')
    def secondaryWeapon = parseWeapon(mech, 'secondary')

    new Mech.Equipment(
      healthUpgrade: healthUpgrade,
      moveUpgrade: moveUpgrade,
      primaryWeapon: primaryWeapon,
      secondaryWeapon: secondaryWeapon
    )
  }

  private Weapon parseWeapon(LuaTable mech, String prefix) {

    if (mech.get(prefix).isnil()) {
      return null
    }

    def type = parseWeaponType(mech.get(prefix).checkjstring())
    def powered = isPowered(mech.get("${prefix}_power").checktable())
    def firstModPowered = isPowered(mech.get("${prefix}_mod1").checktable())
    def secondModPowered = isPowered(mech.get("${prefix}_mod2").checktable())

    new Weapon(
      type: type,
      powered: powered,
      firstModPowered: firstModPowered,
      secondModPowered: secondModPowered
    )
  }

  private Weapon.Type parseWeaponType(String type) {

    switch (type) {
      case 'Prime_Punchmech': return Weapon.Type.TitanFist
      case 'Brute_Tankmech': return Weapon.Type.TaurusCannon
      case 'Ranged_Artillerymech': return Weapon.Type.ArtemisArtillery

      default: throw new IllegalArgumentException("Unknown weapon type [$type]")
    }
  }

  private boolean isPowered(LuaTable item) {
    item.get(1).checkint() > 0
  }

  private List<Enemy> parseEnemies(LuaTable region) {

    def enemyTeamId = 6

    def pawns = findPawns(region)
    def enemies = pawns.findAll { pawn ->
      !pawn.get('mech').checkboolean() &&
        pawn.get('iTeamId').checkint() == enemyTeamId
    }

    def order = 0
    enemies.collect { enemy ->
      parseEnemy(enemy, order++)
    }
  }

  private Enemy parseEnemy(LuaTable enemy, int order) {

    def location = parsePoint(enemy.get('location'))
    def target = parsePoint(enemy.get('piTarget'))
    def health = enemy.get('health').checkint()
    def healthMax = enemy.get('max_health').checkint()

    def type = parseEnemyType(enemy.get('type').checkjstring())

    new Enemy(
      location: location,
      health: health,
      healthMax: healthMax,
      type: type,
      order: order,
      target: target
    )
  }

  private Enemy.Type parseEnemyType(String type) {

    switch (type) {
      case 'Scorpion1': return Enemy.Type.Scorpion
      case 'Firefly1': return Enemy.Type.Firefly
      case 'Hornet1': return Enemy.Type.Hornet
      case 'Jelly_Health1': return Enemy.Type.SoldierPsion

      default: throw new IllegalArgumentException("Unknown enemy type [$type]")
    }
  }

  private List<Npc> parseNpcs(LuaTable region) {

    def enemyTeamId = 6

    def pawns = findPawns(region)
    def npcs = pawns.findAll { pawn ->
      !pawn.get('mech').checkboolean() &&
        pawn.get('iTeamId').checkint() != enemyTeamId
    }

    def order = 0
    npcs.collect { npc ->
      parseNpc(npc, order++)
    }
  }

  private Npc parseNpc(LuaTable npc, int order) {

    def location = parsePoint(npc.get('location'))
    def health = npc.get('health').checkint()
    def healthMax = npc.get('max_health').checkint()

    def type = parseNpcType(npc.get('type').checkjstring())

    new Npc(
      location: location,
      health: health,
      healthMax: healthMax,
      type: type,
      order: order
    )
  }

  private Npc.Type parseNpcType(String type) {

    switch (type) {
      case 'Train_Pawn': return Npc.Type.Train
      case 'Dam_Pawn': return Npc.Type.Dam

      default: throw new IllegalArgumentException("Unknown npc type [$type]")
    }
  }

  private List<LuaTable> findPawns(LuaTable region) {

    def mapData = findMapData(region)
    def pawnCount = mapData.get('pawn_count').checkint()

    (1..pawnCount).collect { i ->
      mapData.get("pawn${i}").checktable()
    }
  }

  private Board parseBoard(LuaTable region) {

    def mapData = findMapData(region)

    def dimensions = parsePoint(mapData.get('dimensions'))
    def width = dimensions.x
    def height = dimensions.y

    def board = new Board(width, height)

    def map = toList(mapData.get('map').checktable())

    map.each {
      def entry = it.checktable()
      def location = parsePoint(entry.get('loc'))
      def tile = parseTile(entry.checktable())

      board.set(location.x, location.y, tile)
    }

    board
  }

  private LuaTable findMapData(LuaTable region) {

    def player = region.get('player').checktable()
    player.get('map_data').checktable()
  }

  private Tile parseTile(LuaTable tile) {

    def value = tile.get('terrain').checkint()

    switch (value) {
      case 0: return new Tile(Terrain.Normal)
      case 1: return new Tile(parseBuilding(tile))
      case 2: return new Tile(Terrain.Normal)  // destroyed building
      case 3: return new Tile(Terrain.Water)
      case 4: return new Tile(parseMountain(tile))
      case 6: return new Tile(Terrain.Forest)

      default: throw new IllegalArgumentException("Unknown terrain [$value]")
    }
  }

  private Building parseBuilding(LuaTable table) {

    def healthMax = table.get('health_max').checkint()
    def healthMin = table.get('health_min').optint(healthMax)

    new Building(health: healthMin, healthMax: healthMax)
  }

  private Mountain parseMountain(LuaTable table) {

    def healthMax = table.get('health_max').optint(2)
    def healthMin = table.get('health_min').optint(healthMax)

    new Mountain(health: healthMin, healthMax: healthMax)
  }

  private Point parsePoint(LuaValue point) {
    new Point(
      point.get('x').checkint(),
      point.get('y').checkint()
    )
  }

  private List<LuaValue> toList(LuaTable table) {
    def varargs = table.unpack()
    (1..varargs.narg()).collect { i ->
      varargs.arg(i)
    }
  }

}
