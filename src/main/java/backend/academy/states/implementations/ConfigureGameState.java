package backend.academy.states.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.states.interfaces.GameState;
import backend.academy.util.ConsoleInteractor;
import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import java.io.IOException;
import java.util.List;

public class ConfigureGameState implements GameState {
    boolean gameIsFinished = false;

    @Override
    public void nextState(GameSession gameSession) {
        gameSession.gameState(new PlayingGameState(gameSession.answer().length()));
    }

    @Override
    public void processState(GameSession gameSession) throws IOException {
        ConsoleInteractor consoleInteractor = gameSession.consoleInteractor();

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

    @Override
    public boolean gameIsFinished() {
        return gameIsFinished;
    }
}
