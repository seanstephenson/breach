package com.srs.breach.game.entity

import com.srs.breach.game.board.Point

interface Mobile {

  Point getLocation()

  int getMoveSpeed()

  boolean isFlying()
  boolean canMove()

  Team getTeam()

}
