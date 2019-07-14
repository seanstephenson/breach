package com.srs.breach.game.board


import com.srs.breach.game.entity.Entity
import com.srs.breach.game.entity.Team

/**
 * A game board, represented as a grid of tiles.
 */
class Board {

  private Tile[] tiles

  BitBoard occupied
  List<Entity> entities

  private BitBoard[] occupiedByTeam
  private List<Entity>[] entitiesByTeam

  Board() {
    tiles = (0..<64).collect { new Tile() }

    entities = []
    occupied = new BitBoard()

    occupiedByTeam = (0..<Team.values().size()).collect { new BitBoard() }
    entitiesByTeam = (0..<Team.values().size()).collect { [] }
  }

  Board(List<List<Tile>> tiles) {
    this()

    tiles.eachWithIndex { row, y ->
      row.eachWithIndex { tile, x ->
        set(x, y, tile)
      }
    }
  }

  Board(Entity... entities) {
    this()

    entities.each { entity ->
      place(entity)
    }
  }

  int getWidth() { 8 }
  int getHeight() { 8 }

  Tile get(int x, int y) {
    tiles[index(x, y)]
  }

  Tile get(Point location) {
    get(location.x, location.y)
  }

  /**
   * Sets the given location to contain the given tile.  If there is an entity in the tile they will be placed on
   * the board at the given location.
   */
  void set(int x, int y, Tile tile) {
    def previous = get(x, y)

    if (previous.entity) {
      remove(previous.entity)
    }

    tiles[index(x, y)] = tile

    def entity = tile.entity
    if (entity) {
      place(entity, x, y)
    }
  }

  void set(Point location, Tile tile) {
    set(location.x, location.y, tile)
  }

  /**
   * Gets the entities on the board that correspond to the given team.
   */
  List<Entity> entities(Team team) {
    entitiesByTeam[team.ordinal()]
  }

  /**
   * Gets the squares on the board that are occupied by the given team.
   */
  BitBoard occupied(Team team) {
    occupiedByTeam[team.ordinal()]
  }

  /**
   * Gets the squares on the board that are occupied by terrain.
   */
  BitBoard getTerrain() {
    occupied(Team.Terrain)
  }

  /**
   * Places an entity on the board at the given location.
   */
  void place(Entity entity, Point location) {
    entity.location = location
    place(entity)
  }

  void place(Entity entity, int x, int y) {
    place(entity, new Point(x, y))
  }

  /**
   * Places an entity on the board at the entity's stated location.
   */
  void place(Entity entity) {

    if (!contains(entity)) {
      entities.add(entity)
      entities(entity.team).add(entity)

      placeInternal(entity)

    } else {
      assert get(entity.location).entity == entity
    }
  }

  private void placeInternal(Entity entity) {

    def locations = [entity.location] + entity.extraLocations
    for (location in locations) {
      def tile = get(location)

      assert tile.entity == null || tile.entity == entity :
        "Tried to place entity [$entity] over existing entity [$tile.entity] at [$location]"

      tile.entity = entity

      occupied.set(location)
      occupiedByTeam[entity.team.ordinal()].set(location)
    }
  }

  /**
   * Removes an entity from the board.
   */
  void remove(Entity entity) {

    if (contains(entity)) {
      entities.remove(entity)
      entities(entity.team).remove(entity)

      removeInternal(entity)
    }
  }

  private void removeInternal(Entity entity) {

    def locations = [entity.location] + entity.extraLocations

    for (location in locations) {
      def tile = get(location)

      assert tile.entity == entity :
        "Tried to remove entity [$entity] from [$location] but that location instead contains [$tile.entity]"

      tile.entity = null

      occupied.clear(location)
      occupiedByTeam[entity.team.ordinal()].clear(location)
    }
  }

  /**
   * Moves the given entity to the given target location.  The entity must already have been placed on the board.
   */
  void move(Entity entity, Point target) {
    removeInternal(entity)
    entity.location = target
    placeInternal(entity)
  }

  void move(Entity entity, int x, int y) {
    move(entity, new Point(x, y))
  }

  void place(List<Entity> entities) {
    for (entity in entities) {
      place(entity)
    }
  }

  boolean contains(Entity entity) {
    entity in entities(entity.team)
  }

  private int index(int x, int y) {
    assertRange(x, y)
    (y * 8) + x
  }

  private void assertRange(int x, int y) {
    assert x >= 0 && x < 8 && y >= 0 && y < 8 : "Out of bounds ($x,$y)"
  }

}
