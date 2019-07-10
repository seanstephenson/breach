package com.srs.breach.game.entity

import com.srs.breach.game.board.Point

import static com.srs.breach.game.entity.Entity.*
import static com.srs.breach.game.entity.Entity.Ability.*

class Enemy extends ActionEntity {

  Type type
  int order

  Point target

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

    Leaper(1, 4, Leaping),
    AlphaLeaper(3, 4, Leaping),

    Beetle(4, 2),
    AlphaBeetle(5, 2),
    BeetleLeader(6, 3, Massive),

    Scarab(2, 3),
    AlphaScarab(4, 3),

    Crab(3, 3),
    AlphaCrab(5, 3),

    Centipede(3, 2),
    AlphaCentipede(5, 2),

    Digger(2, 3),
    AlphaDigger(4, 3),

    Hornet(2, 5, Flying),
    AlphaHornet(4, 5, Flying),
    HornetLeader(6, 3, Flying),

    SoldierPsion(2, 2, Flying),
    ShellPsion(2, 2, Flying),
    BloodPsion(2, 2, Flying),
    BlastPsion(2, 2, Flying),
    PsionTyrant(2, 2, Flying),
    PsionAbomination(5, 3, Flying),

    Spider(2, 2),
    AlphaSpider(4, 2),
    SpiderLeader(6, 2),
    SpiderlingEgg(1, 0),
    Spiderling(1, 3),
    AlphaSpiderling(1, 3),

    Burrower(3, 4, Burrowing),
    AlphaBurrower(5, 4, Burrowing),

    LargeGoo(3, 3, Massive),
    MediumGoo(2, 3, Massive),
    SmallGoo(1, 3, Massive);

    int health
    int move
    List<Ability> abilities

    Type(int health, int move, Ability... abilities) {
      this.health = health
      this.move = move
      this.abilities = abilities.toList()
    }
  }

}
