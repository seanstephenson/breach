package com.srs.breach.ai

import com.srs.breach.game.board.Point

enum Direction {
  East,
  North,
  West,
  South;

  static Direction from(Point origin, Point target) {

    if (origin == target) {
      return null
    }

    int deltaX = target.x - origin.x
    int deltaY = target.y - origin.y

    if (Math.abs(deltaX) > Math.abs(deltaY)) {
      if (deltaX <= 0) {
        West
      } else {
        East
      }
    } else {
      if (deltaY <= 0) {
        North
      } else {
        South
      }
    }
  }

}
