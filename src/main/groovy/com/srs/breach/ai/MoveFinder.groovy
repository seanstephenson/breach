package com.srs.breach.ai

import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Point
import com.srs.breach.game.entity.Mobile

/**
 * Finds all possible move squares for a mobile entity.
 */
class MoveFinder {

  BitBoard find(Mobile mover, Board board) {

    def moves = BitBoard.empty()

    if (!mover.canMove) {
      return moves
    }

    def origin = mover.location
    def speed = mover.moveSpeed

    def occupied = findOccupied(board)

    if (mover.flying) {
      def mask = BitBoard.distance(origin.x, origin.y, speed)
      moves = mask - occupied

    } else {
      def blocked = findBlocked(board, mover)
      depthFirstSearch(origin, moves, blocked, speed)
      moves = moves - occupied
    }

    moves
  }

  private void depthFirstSearch(Point origin, BitBoard moves, BitBoard blocked, int distance) {

    moves.set(origin)

    if (distance > 0) {
      for (next in [origin.west(), origin.north(), origin.east(), origin.south()]) {
        if (inBounds(next) && !blocked.get(next)) {
          depthFirstSearch(next, moves, blocked, distance - 1)
        }
      }
    }
  }

  private boolean inBounds(Point point) {
    point.x >= 0 && point.x < 8 && point.y >= 0 && point.y < 8
  }

  private BitBoard findBlocked(Board board, Mobile mover) {

    def bitBoard = BitBoard.empty()

    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        def tile = board.get(x, y)

        if (tile.entity && tile.entity.team != mover.team) {
          bitBoard.set(x, y)
        }
      }
    }

    bitBoard
  }

  private BitBoard findOccupied(Board board) {

    def bitBoard = BitBoard.empty()

    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        def tile = board.get(x, y)

        if (tile.entity) {
          bitBoard.set(x, y)
        }
      }
    }

    bitBoard
  }

}
