package backend.academy.strategy.implementations;

import backend.academy.game.implementations.GameSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StrategyManagerTest {
    StrategyManager strategyManager = new StrategyManager();
    GameSession gameSession;

    @BeforeEach
    void setup(){
        gameSession = new GameSession();
    }

    @Test
    @DisplayName("Ensure the StrategyManager returns ConfigGameStrategy")
    void ensureStrategyManagerReturnsConfigGameStrategy(){
        assertThat(strategyManager.manageStrategy(gameSession)).isInstanceOf(ConfigGameStrategy.class);
    }

    @Test
    @DisplayName("Ensure the StrategyManager returns PlayGameStrategy")
    void ensureStrategyManagerReturnsPlayGameStrategy(){
        gameSession.NUMBER_OF_ATTEMPTS(10);
        gameSession.answer("answer");
        gameSession.hint("hint");
        assertThat(strategyManager.manageStrategy(gameSession)).isInstanceOf(PlayGameStrategy.class);
    }

    @Test
    @DisplayName("Ensure the StrategyManager returns ResultOfGameStrategy")
    void ensureStrategyManagerReturnsResultOfGameStrategy(){
        gameSession.NUMBER_OF_ATTEMPTS(10);
        gameSession.answer("answer");
        gameSession.hint("hint");
        gameSession.gameResult("win");
        assertThat(strategyManager.manageStrategy(gameSession)).isInstanceOf(ResultOfGameStrategy.class);
    }
}
