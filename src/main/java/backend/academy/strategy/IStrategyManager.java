package backend.academy.strategy;

import backend.academy.game.implementations.GameSession;

public interface IStrategyManager {
    GameStrategy manageStrategy(GameSession gameSession);
}
