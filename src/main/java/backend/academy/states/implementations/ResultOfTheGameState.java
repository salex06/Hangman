package backend.academy.states.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.states.interfaces.GameState;
import backend.academy.util.ConsoleInteractor;
import java.io.IOException;

public class ResultOfTheGameState implements GameState {
    boolean gameIsFinished = false;
    private final boolean gameResult;

    public ResultOfTheGameState(boolean gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public void nextState(GameSession gameSession) {
    }

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

    @Override
    public boolean gameIsFinished() {
        return gameIsFinished;
    }
}
