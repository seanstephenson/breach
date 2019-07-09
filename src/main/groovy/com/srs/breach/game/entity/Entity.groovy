package com.srs.breach.game.entity

import com.srs.breach.game.board.Point

interface Entity {

  Point getLocation()
  void setLocation(Point location)

  int getHealth()
  void setHealth(int health)

  int getHealthMax()
  void setHealthMax(int maxHealth)

  Effect getEffect()
  List<Ability> getAbilities()


  enum Effect {
    Fire,
    Smoke,
    Acid,
    Frozen,
    Webbed,
    Healthy
  }

  enum Ability {
    Massive,
    Armored,
    Flying,
    Leaping,
    Burrowing,
    Stable
  }

}
