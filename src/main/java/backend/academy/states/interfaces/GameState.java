package backend.academy.states.interfaces;

import backend.academy.game.implementations.GameSession;
import java.io.IOException;

/**
 * The interface of the "State" pattern to manage game states
 */
public interface GameState {
    /**
     * Change the current state to the next state
     *
     * @param gameSession the current session of the game
     */
    void nextState(GameSession gameSession);

    /**
     * Handle the current state of the game (configuring the game, playing the game, etc.)
     *
     * @param gameSession the current session of the game
     * @throws IOException if an input/output error has occurred
     */
    void processState(GameSession gameSession) throws IOException;

    /**
     * Check the game is finished
     *
     * @return boolean type value - the result of the check
     */
    boolean gameIsFinished();
}
