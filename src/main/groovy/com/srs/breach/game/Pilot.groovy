package com.srs.breach.game

class Pilot {

  Type type
  String name

  int experience
  int level

  List<Skill> skills

  enum Type {
    Starter,      // randomly generated starter pilot
    RalphKarlsen
  }

  enum Skill {
    Move,
    Power,
    Health,
    GridDefense
  }

}
