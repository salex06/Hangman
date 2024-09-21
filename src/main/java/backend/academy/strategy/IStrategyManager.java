package backend.academy.strategy;

import backend.academy.game.implementations.GameSession;

/**
 * The IStrategyManager interface provides the operation
 * which allows get the current strategy for the game according to
 * the information in the model of the game
 */
public interface IStrategyManager {
    /**
     * The method of managing game strategies
     *
     * @param gameSession the model of the game which contains all the necessary info
     * @return GameStrategy type object which defines the behaviour of the current game state
     */
    GameStrategy manageStrategy(GameSession gameSession);
}
