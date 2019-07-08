package com.srs.breach.game

import com.srs.breach.game.board.Board

class Mission {

  Board board

  List<ObjectiveStatus> objectiveStatuses
  int remainingTurns

  class ObjectiveStatus {
    Objective objective
    int completed
    int required
  }

}
