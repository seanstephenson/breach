package com.srs.breach.game

class Island {

  enum Type {
    Grass,
    Desert,
    Snow,
    Factory,
    Volcano
  }

  Type type
  List<Region> regions

}
