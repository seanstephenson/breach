package com.srs.breach.game.parser.lua

import com.srs.breach.game.Game
import com.srs.breach.game.Pilot
import com.srs.breach.game.Weapon
import com.srs.breach.game.board.Terrain
import com.srs.breach.game.board.Tile
import com.srs.breach.game.entity.Building
import com.srs.breach.game.entity.Mech
import com.srs.breach.game.entity.Mountain
import org.luaj.vm2.Globals
import org.luaj.vm2.LuaTable
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

    def gameData = globals.get('GameData')
    def regionData = globals.get('RegionData')
    def squadData = globals.get('SquadData')

    def reputation = squadData.get('money').toint()
    def cores = squadData.get('cores').toint()

    new Game(
      reputation: reputation,
      cores: cores,

    )
  }

  private Tile parseTile(LuaTable table) {

    def value = table.get('terrain')

    switch (value) {
      case 0: return new Tile(Terrain.Normal)
      case 1: return new Tile(Building.doubleBuilding())
      case 2: return new Tile(Terrain.Normal)  // destoryed building
      case 3: return new Tile(Terrain.Water)
      case 4: return new Tile(Mountain.create())
      case 6: return new Tile(Terrain.Forest)

      default: throw new IllegalArgumentException("Unknown terrain [$value]")
    }
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

}
