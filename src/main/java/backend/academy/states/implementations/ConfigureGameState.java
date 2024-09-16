package backend.academy.states.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.states.interfaces.GameState;
import backend.academy.util.ConsoleInteractor;
import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import java.io.IOException;
import java.util.List;

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

        consoleInteractor.writeMessage("Выберите максимальное количество ошибок (от 6): ");
        consoleInteractor.flushBuffer();
        int numberOfAttempts = Integer.parseInt(consoleInteractor.readMessage());
        if (numberOfAttempts < GameSession.MINIMAL_NUMBER_OF_ATTEMPTS()) {
            throw new IllegalArgumentException(
                "Количество возможных ошибок не менее " + GameSession.MINIMAL_NUMBER_OF_ATTEMPTS()
            );
        }
        gameSession.NUMBER_OF_ATTEMPTS(numberOfAttempts);

        consoleInteractor.writeMessage("Доступные категории");
        List<String> availableCategories = Category.getCategoryAsStringList();
        for (int i = 0; i < availableCategories.size(); i++) {
            consoleInteractor.writeMessage("\n" + "[" + (i + 1) + "]" + availableCategories.get(i));
        }
        consoleInteractor.writeMessage("\nВведите номер категории: ");
        consoleInteractor.flushBuffer();
        Category category = Category.getCategory(consoleInteractor.readMessage());

        consoleInteractor.writeMessage("Доступные уровни сложности слова");
        List<String> availableLevels = Level.getLevelAsStringList();
        for (int i = 0; i < availableLevels.size(); i++) {
            consoleInteractor.writeMessage("\n" + "[" + (i + 1) + "]" + availableLevels.get(i));
        }
        consoleInteractor.writeMessage("\nВведите номер уровня сложности: ");
        consoleInteractor.flushBuffer();
        Level level = Level.getLevel(consoleInteractor.readMessage());

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
