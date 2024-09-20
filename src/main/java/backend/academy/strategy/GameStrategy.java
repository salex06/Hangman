package backend.academy.strategy;

import backend.academy.game.implementations.GameSession;

/**
 * The GameStrategy interface provides the operation to
 * handle different states of the game and change the state
 * of the model of the game
 */
public interface GameStrategy {
    /**
     * Processes the strategy for the different states of the game
     *
     * @param data        the necessary information about the current game state
     * @param gameSession the model of the current session
     */
    void processStrategy(String data, GameSession gameSession);
}
