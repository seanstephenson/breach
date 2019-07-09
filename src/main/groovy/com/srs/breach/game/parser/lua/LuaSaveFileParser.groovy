package com.srs.breach.game.parser.lua

import com.srs.breach.game.Game
import com.srs.breach.game.Mission
import com.srs.breach.game.Pilot
import com.srs.breach.game.Weapon
import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Terrain
import com.srs.breach.game.board.Tile
import com.srs.breach.game.entity.Building
import com.srs.breach.game.entity.Enemy
import com.srs.breach.game.entity.Mech
import com.srs.breach.game.entity.Mountain
import groovy.transform.Immutable
import org.luaj.vm2.Globals
import org.luaj.vm2.LuaTable
import org.luaj.vm2.LuaValue
import org.luaj.vm2.lib.jse.JsePlatform

/**
 * Parses the game state from a lua save file.
 */
class LuaSaveFileParser {

  Game parse(String text) {

    def globals = createGlobals()

    def chunk = globals.load(text)
    chunk.call()

    parseGame(globals)
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
    def board = parseBoard(activeRegion)

    new Game(
      mechs: mechs,
      grid: grid,
      gridMax: gridMax,
      reputation: reputation,
      cores: cores,
      mission: new Mission(
        board: board,
        enemies: enemies
      )
    )
  }

  private LuaTable findActiveRegion(LuaTable regionData) {

    def active = regionData.get('iBattleRegion').checkint()
    regionData.get("region${active}").checktable()
  }

  private List<Mech> parseMechs(LuaTable region) {
    []
  }

  private List<Enemy> parseEnemies(LuaTable region) {
    []
  }

  private Board parseBoard(LuaTable region) {
    def player = region.get('player').checktable()
    def mapData = player.get('map_data').checktable()

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

  private Tile parseTile(LuaTable table) {

    def value = table.get('terrain').checkint()

    switch (value) {
      case 0: return new Tile(Terrain.Normal)
      case 1: return new Tile(parseBuilding(table))
      case 2: return new Tile(Terrain.Normal)  // destroyed building
      case 3: return new Tile(Terrain.Water)
      case 4: return new Tile(parseMountain(table))
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

  private Mech.Type parseMechType(String value) {
    switch (value) {
      case 'PunchMech': return Mech.Type.CombatMech
      case 'TankMech': return Mech.Type.CannonMech
      case 'ArtiMech': return Mech.Type.ArtilleryMech

      default: throw new IllegalArgumentException("Unknown mech type [$value]")
    }

  }

  private Pilot.Type parsePilotType(String value) {
    switch (value) {
      case 'Pilot_Original': return Pilot.Type.RalphKarlsen

      case 'Pilot_Detritus':
      case 'Pilot_Pinnacle': return Pilot.Type.Starter

      default: throw new IllegalArgumentException("Unknown pilot type [$value]")
    }
  }

  private Weapon.Type parseWeaponType(String value) {
    switch (value) {
      case 'Prime_Punchmech': return Weapon.Type.TitanFist
      case 'Brute_Tankmech': return Weapon.Type.TaurusCannon
      case 'Ranged_Artillerymech': return Weapon.Type.ArtemisArtillery

      default: throw new IllegalArgumentException("Unknown weapon type [$value]")
    }
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

  @Immutable
  private static class Point {
    int x, y
  }

}
