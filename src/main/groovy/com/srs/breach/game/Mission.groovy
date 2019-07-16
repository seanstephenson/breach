package com.srs.breach.game

import com.srs.breach.game.board.Board
import com.srs.breach.game.entity.Enemy
import com.srs.breach.game.entity.Npc

class Mission {

  Board board

  int remainingTurns

  List<Enemy> enemies
  List<Npc> npcs

  void init() {
    enemies.each { enemy -> enemy.init() }
    npcs.each { npc -> npc.init() }
  }

}
