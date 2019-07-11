package com.srs.breach.ai

import com.srs.breach.game.board.Board
import com.srs.breach.game.entity.Mobile

/**
 * Finds all possible move squares for a mobile entity.
 */
class MoveFinder {

  BitBoard find(Mobile mover, Board board) {

    def moves = BitBoard.empty()

    if (!mover.canMove()) {
      return moves
    }

    def origin = mover.location
    def speed = mover.moveSpeed

    def occupied = findOccupied(board)
    def blocked = findBlocked(board, mover)

    def mask = manhattanMask(speed).shift(origin.x - speed, origin.y - speed)

    mask - occupied - blocked
  }

  private BitBoard findBlocked(Board board, Mobile mover) {

    def bitBoard = BitBoard.empty()

    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        def tile = board.get(x, y)

        if (tile.entity && tile.entity.team != mover.team) {
          bitBoard.set(x, y, true)
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
          bitBoard.set(x, y, true)
        }
      }
    }

    bitBoard
  }

  private static BitBoard manhattanMask(int moveSpeed) {

    def mask = new BitBoard()

    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        if (Math.abs(moveSpeed - x) + Math.abs(moveSpeed - y) <= moveSpeed) {
          mask.set(x, y, true)
        }
      }
    }

    mask
  }

}
