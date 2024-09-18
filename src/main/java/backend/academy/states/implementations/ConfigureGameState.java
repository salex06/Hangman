package backend.academy.states.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.states.GameState;
import backend.academy.util.ConsoleInteractor;
import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import java.io.IOException;

/**
 * ConfigureGameState class implements GameState interface and handles the initial state of the game -
 * setting up game settings
 */
public class ConfigureGameState implements GameState {
    private boolean gameIsFinished = false;

    /**
     * Sets the game state as PlayingGameState
     *
     * @param gameSession the current session of the game
     */
    @Override
    public void nextState(GameSession gameSession) {
        gameSession.gameState(new PlayingGameState(gameSession.answer().value().length()));
    }

    /**
     * Handles the game configuration process
     *
     * @param gameSession the current session of the game
     * @throws IOException if an input/output error has occurred
     */
    @Override
    public void processState(GameSession gameSession) throws IOException {
        ConsoleInteractor consoleInteractor = gameSession.consoleInteractor();

        consoleInteractor.writeMessage("Select the maximum number of mistakes (at least 6): ");
        consoleInteractor.flushBuffer();
        int numberOfAttempts = Integer.parseInt(consoleInteractor.readMessage());
        if (numberOfAttempts < GameSession.MINIMAL_NUMBER_OF_ATTEMPTS()) {
            throw new IllegalArgumentException(
                "The number of possible mistakes is at least " + GameSession.MINIMAL_NUMBER_OF_ATTEMPTS()
            );
        }
        gameSession.NUMBER_OF_ATTEMPTS(numberOfAttempts);

        consoleInteractor.writeMessage("Available categories");
        for (int i = 0; i < Category.values().length; i++) {
            consoleInteractor.writeMessage(
                "\n" + "[" + (i + 1) + "]" + Category.getCategory(String.valueOf(i + 1)).orElseThrow().name());
        }
        consoleInteractor.writeMessage("\nEnter the category number: ");
        consoleInteractor.flushBuffer();
        Category category = Category.getCategory(consoleInteractor.readMessage()).orElseGet(Category::randomCategory);

        consoleInteractor.writeMessage("Available difficulty levels of the word");
        for (int i = 0; i < Level.values().length; i++) {
            consoleInteractor.writeMessage(
                "\n" + "[" + (i + 1) + "]" + Level.getLevel(String.valueOf(i + 1)).orElseThrow().name());
        }
        consoleInteractor.writeMessage("\nEnter the level number: ");
        consoleInteractor.flushBuffer();
        Level level = Level.getLevel(consoleInteractor.readMessage()).orElseGet(Level::randomLevel);

        gameSession.answer(gameSession.wordsStorage().getRandomWord(category, level));

        consoleInteractor.clearConsole();
        nextState(gameSession);
    }

    /**
     * Check the game is finished (it's always false, because the game has not finished yet)
     *
     * @return boolean type variable "gameIsFinished"
     */
    @Override
    public boolean gameIsFinished() {
        return gameIsFinished;
    }
}
