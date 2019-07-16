package com.srs.breach.ai

import com.srs.breach.game.Game
import com.srs.breach.game.entity.Actor
import com.srs.breach.game.entity.Team

class AutoPlayer {

  ActionFinder actionFinder = new ActionFinder()
  Evaluator evaluator = new Evaluator()

  SearchContext searchContext

  Turn selectTurn(Game game) {

    if (searchContext) {
      throw new IllegalStateException('Search is already in progress')
    }

    searchContext = new SearchContext(
      game: game,
      simulator: new GameSimulator(game)
    )

    // Perform the search.
    search(searchContext)

    searchContext.end()

    // Return the best turn that was found.
    searchContext.best
  }

  private void search(SearchContext context) {

    def game = context.game
    def simulator = context.simulator
    def actions = context.actions

    def possible = findPossibleActions(game)

    if (actions.empty) {
      context.rootActionCount = possible.size()
    }

    if (possible.empty) {

      // This is a leaf node, so end the turn and then evaluate the position.
      simulator.endTurn()

      def score = evaluator.evaluate(game)

      if (!context.best || score > context.best.score) {
        context.best = new Turn(
          actions: context.actions,
          score: score
        )
      }

      // Restore the game to the previous state.
      simulator.undo()

    } else {

      // There are still actions to perform, so perform each action and then recurse.
      for (action in possible) {

        // Perform the action.
        actions.add(action)
        simulator.action(action)

        // Continue the search.
        search(context)

        // Restore the game to the previous state.
        simulator.undo()
        actions.removeLast()

        context.actionsEvaluated++

        if (actions.empty) {
          context.rootActionsEvaluated++
        }
      }
    }
  }

  private List<Action> findPossibleActions(Game game) {

    def board = game.mission.board
    def actors = board.entities(Team.Player) as List<Actor>

    def actions = []
    for (actor in actors) {
      actions.addAll(actionFinder.find(actor, game))
    }

    actions
  }

  static class SearchContext {

    /**
     * The game that is being searched.
     */
    Game game

    /**
     * The best turn found so far.
     */
    Turn best

    /**
     * The simulator used to perform previous the previous actions in the turn.
     */
    GameSimulator simulator

    /**
     * The list of actions currently being evaluated.
     */
    List<Action> actions = []

    /**
     * The number of actions that have been evaluated.
     */
    long actionsEvaluated

    /**
     * The number of possible actions at the root position.
     */
    int rootActionCount
    int rootActionsEvaluated

    /**
     * The time when the search began.
     */
    private long start = System.nanoTime()
    private long end

    private void end() {
      end = System.nanoTime()
    }

    double getElapsed() {
      long end = this.end ?: System.nanoTime()
      (end - start) / 1e9
    }

    double getActionsPerSecond() {
      actionsEvaluated / elapsed
    }

    double getPercentComplete() {
      if (rootActionCount) {
        (rootActionsEvaluated / rootActionCount) * 100.0
      } else {
        0.0
      }
    }

  }

}
