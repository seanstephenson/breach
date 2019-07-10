package com.srs.breach.game.parser.simple

import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Terrain
import com.srs.breach.game.board.Tile

import static com.srs.breach.game.parser.simple.SimpleBreachNotation.*

/**
 * Converts a board to simple breach notation.
 */
class SimpleBoardFormatter {

  String format(Board board) {

    // Format all the tiles.
    def tiles = formatTiles(board)

    // Find the widest tile in each column.
    def widths = (0..<board.width).collect { x ->
      def column = (0..<board.height).collect { y ->
        tiles[y][x]
      }
      def width = column*.length().max() as Integer
      Math.max(2, width)
    }

    // Pad each column to the max width.
    widths.eachWithIndex{ width, x ->
      board.height.times { y ->
        tiles[y][x] = tiles[y][x].padRight(width)
      }
    }

    // Join everything together.
    tiles.collect { row ->
      row.join(' ').trim()
    }.join('\n')
  }

  private List<List<String>> formatTiles(Board board) {
    (0..<board.height).collect { y ->
      (0..<board.width).collect { x ->
        formatTile(board.get(x, y))
      } as List<String>
    }
  }

  private String formatTile(Tile tile) {

    def terrain = toSymbol(tile.terrain)
    assert terrain

    def effect = toSymbol(tile.effect) ?: ''

    def spawnPoint = tile.spawnPoint ? spawnPointSymbol() : ''

    def entity = toSymbol(tile.entity) ?: ''
    if (entity == terrain) entity = ''

    if (tile.terrain == Terrain.Normal && (effect || spawnPoint || entity)) {
      terrain = ''
    }

    "${entity}${terrain}${effect}${spawnPoint}"
  }

}
