package com.srs.breach.game.entity

import static com.srs.breach.game.entity.Entity.*
import static com.srs.breach.game.entity.Entity.Ability.*

class Enemy extends AbstractEntity {

  Type type
  int order

  enum Type {

    Blobber(3, 2),
    AlphaBlobber(4, 2),
    Blob(1, 0),
    AlphaBlob( 1, 0),

    Scorpion(3, 3),
    AlphaScorpion(5, 3),
    ScorpionLeader(7, 3, Massive),

    Firefly(3, 2),
    AlphaFirefly(5, 2),
    FireflyLeader(6, 3, Massive),

    Leaper,
    AlphaLeaper,

    Beetle,
    AlphaBeetle,

    Scarab,
    AlphaScarab,

    Crab,
    AlphaCrab,

    Centipede,
    AlphaCentipede,

    Digger,
    AlphaDigger,

    Hornet,
    AlphaHornet,
    HornetLeader,

    SoldierPsion,
    ShellPsion,
    BloodPsion,
    BlastPsion,
    PsionTyrant,

    Spider,
    AlphaSpider,
    SpiderLeader,
    SpiderlingEgg,
    Spiderling,
    AlphaSpiderling,

    Burrower,
    AlphaBurrower,

    LargeGoo,
    MediumGoo,
    SmallGoo;

    int health
    int move
    List<Ability> abilities

    Type() {
    }

    Type(int health, int move, Ability... abilities) {
      this.health = health
      this.move = move
      this.abilities = abilities.toList()
    }
  }

}
