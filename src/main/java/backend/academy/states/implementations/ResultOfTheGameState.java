package backend.academy.states.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.states.interfaces.GameState;
import backend.academy.util.ConsoleInteractor;
import java.io.IOException;

/**
 * ResultOfTheGameState class implements GameState interface and handles the state of the game if
 * it finished (the user won or lost)
 */
public class ResultOfTheGameState implements GameState {
    private boolean gameIsFinished = false;
    private final boolean gameResult;

    /**
     * Class constructor with one boolean type argument
     *
     * @param gameResult result of the game (the user won or lost)
     */
    public ResultOfTheGameState(boolean gameResult) {
        this.gameResult = gameResult;
    }

    /**
     * Does nothing because the next state is undefined (the game is over)
     *
     * @param gameSession the current session of the game
     */
    @Override
    public void nextState(GameSession gameSession) {
    }

    /**
     * Process game results
     *
     * @param gameSession the current session of the game
     * @throws IOException if an input/output error has occurred
     */
    @Override
    public void processState(GameSession gameSession) throws IOException {
        ConsoleInteractor consoleInteractor = gameSession.consoleInteractor();
        if (gameResult) {
            consoleInteractor.writeMessage("Поздравляем! Вы победили!");
        } else {
            consoleInteractor.writeMessage("К сожалению, вы проиграли!");
        }
        consoleInteractor.writeMessage("Загаданное слово: " + gameSession.answer());
        consoleInteractor.flushBuffer();
        gameIsFinished = true;
    }

    /**
     * Check the game is finished
     *
     * @return boolean type variable "gameIsFinished"
     */
    @Override
    public boolean gameIsFinished() {
        return gameIsFinished;
    }
}
