package com.srs.breach.game

class Mission {

  Region region

  List<ObjectiveStatus> objectiveStatuses
  int remainingTurns

  class ObjectiveStatus {
    Objective objective
    int completed
    int required
  }

}
