package backend.academy.strategy.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.strategy.GameStrategy;
import backend.academy.strategy.IStrategyManager;
import java.util.Objects;

public class StrategyManager implements IStrategyManager {
    @Override
    public GameStrategy manageStrategy(GameSession gameSession) {
        if (Objects.isNull(gameSession.answer()) || Objects.isNull(gameSession.hint())
            || Objects.isNull(gameSession.NUMBER_OF_ATTEMPTS())) {
            return new ConfigGameStrategy();
        } else if (Objects.isNull(gameSession.gameResult())) {
            return new PlayGameStrategy();
        } else {
            return new ResultOfGameStrategy();
        }
    }
}
