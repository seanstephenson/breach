package com.srs.breach.game.parser

import com.srs.breach.game.board.Terrain
import com.srs.breach.game.board.Tile
import com.srs.breach.game.entity.Building
import com.srs.breach.game.entity.Mountain
import spock.lang.Specification

class BoardParserSpec extends Specification {

  def parser = new BoardParser()

  def 'Terrain is parsed'() {
    when:
    def board = parser.parse('''
      * . .
      . . _
    ''')

    then:
    board.get(0, 0).terrain == Terrain.Normal
    board.get(2, 0).terrain == Terrain.Chasm
    board.get(0, 1).terrain == Terrain.Sand
  }

  def 'Effects are parsed'() {
    when:
    def board = parser.parse('''
      . *a  f
      .  s  .
    ''')

    then:
    board.get(0, 0).terrain == Terrain.Normal
    board.get(0, 0).effect == Tile.Effect.None

    board.get(1, 0).terrain == Terrain.Normal
    board.get(1, 0).effect == Tile.Effect.Smoke

    board.get(1, 1).terrain == Terrain.Sand
    board.get(1, 1).effect == Tile.Effect.Acid

    board.get(2, 1).terrain == Terrain.Normal
    board.get(2, 1).effect == Tile.Effect.Fire
  }

  def 'Buildings are parsed'() {
    when:
    def board = parser.parse('''
      . *Bf .
      B  b  .
    ''')

    then:
    board.get(0, 0).entity instanceof Building
    (board.get(0, 0).entity as Building).health == 2

    board.get(1, 1).terrain == Terrain.Sand
    board.get(1, 1).entity instanceof Building
    (board.get(1, 1).entity as Building).health == 2
    board.get(1, 1).effect == Tile.Effect.Fire

    board.get(1, 0).entity instanceof Building
    (board.get(1, 0).entity as Building).health == 1
  }

  def 'Mountains are parsed'() {
    when:
    def board = parser.parse('''
      . *Mf .
      m  M  .
    ''')

    then:
    board.get(0, 0).entity instanceof Mountain
    (board.get(0, 0).entity as Mountain).damaged

    board.get(1, 1).terrain == Terrain.Sand
    board.get(1, 1).entity instanceof Mountain
    !(board.get(1, 1).entity as Mountain).damaged
    board.get(1, 1).effect == Tile.Effect.Fire

    board.get(1, 0).entity instanceof Mountain
    !(board.get(1, 0).entity as Mountain).damaged
  }

  def 'Spawn points are parsed'() {
    when:
    def board = parser.parse('''
      ^  .
      . ^!
    ''')

    then:
    board.get(0, 1).terrain == Terrain.Normal
    board.get(0, 1).spawnPoint

    board.get(1, 0).terrain == Terrain.Forest
    board.get(1, 0).spawnPoint

    !board.get(0, 0).spawnPoint
    !board.get(1, 1).spawnPoint
  }

  def 'Invalid - multiple terrain markers'() {
    when:
    parser.parse('''
      . . ._
    ''')

    then:
    def error = thrown(IllegalArgumentException)
    error.message == 'Multiple terrain type indicators [_] found in tile [._]'
  }

}
