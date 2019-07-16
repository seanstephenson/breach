package com.srs.breach.game

import com.srs.breach.game.entity.Mech

class Game {

  Mission mission

  List<Mech> mechs

  int grid
  int gridMax

  int reputation
  int cores

  void init() {
    mission.init()
    mechs.each { mech -> mech.init() }
  }

}
