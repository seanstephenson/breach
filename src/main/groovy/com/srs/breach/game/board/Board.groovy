package com.srs.breach.game.board

import com.srs.breach.game.entity.Entity

/**
 * A game board, represented as a grid of tiles.
 *
 * In game UI coordinates, the ranks are represented as numbers 1-8 and files are letters A-H.
 *
 * 8 . . . . . . . .
 * 7 . . . . . . . .
 * 6 . . . . . . . .
 * 5 . . . . . . . .
 * 4 . . . . . . . .
 * 3 . . . . . . . .
 * 2 . . . x . . . .
 * 1 . . . . . . . .
 *   A B C D E F G H
 *
 * In this class, both ranks and files are represented numerically from 0-7 (or more for non-standard size boards).
 *
 * For example, in the above diagram: x == D2 == (3, 1)
 *
 */
class Board {

  int width, height

  private Tile[] tiles

  Board(int width, int height) {

    this.width = width
    this.height = height

    def size = width * height
    tiles = (1..size).collect { new Tile() }
  }

  Board(List<List<Tile>> tiles) {

    assert !tiles.empty : 'Tiles cannot be empty'

    height = tiles.size()
    width = tiles.first().size()

    def size = width * height
    this.tiles = new Tile[size]

    assert width > 0 : "Width must be positive, width=$width"
    tiles.eachWithIndex { row, y ->

      assert row.size() == width : "At row [$y], expected width [$width], but got [${row.size()}]"

      row.eachWithIndex { tile, x ->
        set(x, y, tile)
      }
    }
  }

  Tile get(int x, int y) {
    tiles[index(x, y)]
  }

  Tile get(Point location) {
    get(location.x, location.y)
  }

  void set(int x, int y, Tile tile) {
    tiles[index(x, y)] = tile
    tile.entity?.setLocation(new Point(x, y))
  }

  void set(Point location, Tile tile) {
    set(location.x, location.y, tile)
  }

  void place(List<Entity> entities) {
    entities.each { entity ->
      def locations = [entity.location] + entity.extraLocations
      locations.each { location ->
        get(location).entity = entity
      }
    }
  }

  private int index(int x, int y) {
    assertRange(x, y)
    y * width + x
  }

  private void assertRange(int x, int y) {
    assert x >= 0 && x < width : "x=$x, width=$width"
    assert y >= 0 && y < height : "y=$y, height=$height"
  }

}
