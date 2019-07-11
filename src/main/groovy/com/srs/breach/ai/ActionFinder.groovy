package com.srs.breach.ai


import com.srs.breach.game.Game
import com.srs.breach.game.entity.Actor
import com.srs.breach.game.entity.Mobile

class ActionFinder {

  List<Action> getPossibleActions(Actor actor, Game game) {

  }

  List<Action> getPossibleMoves(Mobile mover) {

    if (!mover.canMove()) {
      return []
    }


  }

}
