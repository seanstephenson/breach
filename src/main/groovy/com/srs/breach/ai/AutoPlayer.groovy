package com.srs.breach.ai


import com.srs.breach.game.Game

class AutoPlayer {

  def actionFinder = new ActionFinder()
  def simulator = new GameSimulator()
  def evaluator = new Evaluator()

  List<Action> getTurnActions(Game game) {

    def actors = []

    for (actor in actors) {

      // Find all possible actions for the given entity.
      def actions = actionFinder.find(actor, game)

      for (action in actions) {

        // Perform each action.
        simulator.action(action)

        // Evaluate the position and assign a score.
        def score = evaluator.evaluate(game)

        // todo: keep track of the best score
      }
    }

    []
  }

}
