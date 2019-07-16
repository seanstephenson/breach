package com.srs.breach.ai

import com.srs.breach.game.Game
import com.srs.breach.game.Mission
import com.srs.breach.game.Weapon
import com.srs.breach.game.board.Board
import com.srs.breach.game.board.Point
import com.srs.breach.game.entity.Actor

class GameSimulator {

  Game game
  private Mission mission
  private Board board

  private List<Undoer> undoers = []

  GameSimulator(Game game) {
    this.game = game
    this.mission = game.mission
    this.board = game.mission.board
  }

  /**
   * Performs the given action on the game.
   */
  void action(Action action) {
    if (action.type == Action.Type.Move) {
      doMoveAction(action.actor, action.target)
    } else if (action.type == Action.Type.Weapon) {
      doWeaponAction(action.actor, action.weapon, action.target)
    } else if (action.type == Action.Type.None) {
      doNoneAction(action.actor)
    } else {
      throw new IllegalArgumentException("Unknown action type [$action.type]")
    }
  }

  /**
   * Simulates the end of the turn, including the enemy actions.
   */
  void endTurn() {
    undoers.add(new Undoer() {
      @Override
      void undo() {
      }
    })
  }

  private void doMoveAction(Actor actor, Point target) {

    undoers << new MoveUndoer(actor)

    game.mission.board.move(actor, target)

    actor.moved = true
    actor.canMove = false
  }

  private void doWeaponAction(Actor actor, Weapon weapon, Point target) {
    //undoers << new WeaponUndoer(actor, weapon, target)
    doNoneAction(actor)
  }

  void doNoneAction(Actor actor) {

    undoers << new NoneUndoer(actor)

    actor.moved = true
    actor.canMove = false
    actor.acted = true
    actor.canAct = false
  }

  /**
   * Reverse the last action that was performed.
   */
  void undo() {
    def undoer = undoers.removeLast()
    undoer.undo()
  }

  private interface Undoer {
    void undo()
  }

  private class MoveUndoer implements Undoer {
    Actor actor
    Point location
    boolean moved
    boolean canMove

    MoveUndoer(Actor actor) {
      this.actor = actor
      location = actor.location
      canMove = actor.canMove
      moved = actor.moved
    }

    @Override
    void undo() {
      board.move(actor, location)
      actor.moved = moved
      actor.canMove = canMove
    }
  }

  private class WeaponUndoer implements Undoer {
    Actor actor
    Weapon weapon
    Point target

    WeaponUndoer(Actor actor, Weapon weapon, Point target) {
      this.actor = actor
      this.weapon = weapon
      this.target = target
    }

    @Override
    void undo() {
      // todo: implement this
    }
  }

  private class NoneUndoer implements Undoer {
    Actor actor
    boolean moved
    boolean canMove
    boolean acted
    boolean canAct

    NoneUndoer(Actor actor) {
      this.actor = actor
      moved = actor.moved
      canMove = actor.canMove
      acted = actor.acted
      canAct = actor.canAct
    }

    @Override
    void undo() {
      actor.moved = moved
      actor.canMove = canMove
      actor.acted = acted
      actor.canAct = canAct
    }
  }

}
