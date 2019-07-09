package com.srs.breach.game.board

class Point {

  int x, y

  Point(int x, int y) {
    this.x = x
    this.y = y
  }

  @Override
  String toString() {
    "($x,$y)"
  }

}
