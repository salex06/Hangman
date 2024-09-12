package backend.academy.states.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.states.interfaces.GameState;
import backend.academy.util.ConsoleInteractor;
import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import java.io.IOException;


public class ConfigureGameState implements GameState {
    boolean gameIsFinished = false;

    @Override
    public void nextState(GameSession gameSession) {
        gameSession.gameState(new PlayingGameState(gameSession.answer().length()));
    }

    @Override
    public void processState(GameSession gameSession) throws IOException {
        ConsoleInteractor consoleInteractor = gameSession.consoleInteractor();

        consoleInteractor.writeMessage("Введите категорию: ");
        consoleInteractor.flushBuffer();
        Category category = Category.getCategory(consoleInteractor.readMessage());

        consoleInteractor.writeMessage("Введите уровень сложности: ");
        consoleInteractor.flushBuffer();
        Level level = Level.getLevel(consoleInteractor.readMessage());

        gameSession.answer(gameSession.wordsStorage().getRandomWord(category, level));

        nextState(gameSession);
    }

    @Override
    public boolean gameIsFinished() {
        return gameIsFinished;
    }
}
