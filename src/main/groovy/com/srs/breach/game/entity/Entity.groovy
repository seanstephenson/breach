package com.srs.breach.game.entity

interface Entity {

  int getX()
  int getY()

  void setLocation(int x, int y)

  int getHealth()
  void setHealth(int health)

  int getMaxHealth()
  void setMaxHealth(int maxHealth)

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
    Stable
  }

}
