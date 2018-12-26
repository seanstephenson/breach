package com.srs.breach.game

import com.srs.breach.game.entity.Mech

class Game {

  int reputation
  int cores

  List<Mech> mechs
  List<Island> islands

  List<Pilot> storedPilots
  List<Weapon.Type> storedWeapons

  Mission mission

}
