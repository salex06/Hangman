package backend.academy.strategy.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.strategy.GameStrategy;

public class ResultOfGameStrategy implements GameStrategy {
    @Override
    public void processStrategy(String data, GameSession gameSession) {
        gameSession.gameIsFinished(true);
    }
}
