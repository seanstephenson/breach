package com.srs.breach.ai

import com.srs.breach.game.Game
import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Point
import com.srs.breach.game.entity.Actor

class GameSimulator {

  Game game

  private List<Undoer> undoers = []

  /**
   * Performs the given action on the game.
   */
  void action(Action action) {
    if (action.type == Action.Type.Move) {
      doMoveAction(action.actor, action.target)
    } else if (action.type == Action.Type.Weapon) {
      doWeaponAction(action.actor, action.target)
    } else {
      throw new IllegalArgumentException("Unknown action type [$action.type]")
    }
  }

  private void doMoveAction(Actor actor, Point target) {
    def origin = actor.location

    undoers << new MoveUndoer(actor: actor, before: origin)
    game.mission.board.move(origin, target)
  }

  private void doWeaponAction(Actor actor, Point target) {
    
  }

  /**
   * Reverse the last action that was performed.
   */
  void undo() {
    def undoer = undos.removeLast()
  }

  private interface Undoer {
    void undo()
  }

  private class MoveUndoer implements Undoer {
    Actor actor
    Point before

    @Override
    void undo() {
      board.move(actor.location, before)
    }
  }

  private class CloneUndoer implements Undoer {
    Board before

    @Override
    void undo() {
      // todo: implement
    }
  }

}
