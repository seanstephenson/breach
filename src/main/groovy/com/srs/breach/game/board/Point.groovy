package com.srs.breach.game.board

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Point {

  int x, y

  Point(int x, int y) {
    this.x = x
    this.y = y
  }

  Point north() {
    new Point(x, y - 1)
  }

  Point east() {
    new Point(x + 1, y)
  }

  Point south() {
    new Point(x, y + 1)
  }

  Point west() {
    new Point(x - 1, y)
  }

  @Override
  String toString() {
    "($x,$y)"
  }

}
