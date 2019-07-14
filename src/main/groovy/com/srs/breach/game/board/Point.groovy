package com.srs.breach.game.board

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
  boolean equals(Object object) {
    def other = (Point) object
    x == other.x && y == other.y
  }

  @Override
  int hashCode() {
    Integer.hashCode(x * 8 + y)
  }

  @Override
  String toString() {
    "($x,$y)"
  }

}
