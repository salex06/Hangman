package backend.academy.strategy.implementations;

import backend.academy.game.implementations.GameOrchestrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResultOfGameStrategyTest {
    GameOrchestrator gameOrchestrator;

    @BeforeEach
    void setup() {
        gameOrchestrator = new GameOrchestrator();
        gameOrchestrator.gameStrategy(new ResultOfGameStrategy());
    }

    @Test
    @DisplayName("Ensure that the method works correctly")
    void ensureProcessStrategyChangesGameIsFinishedVariable(){
        gameOrchestrator.gameStrategy().processStrategy("", gameOrchestrator.gameSession());
        assertThat(gameOrchestrator.gameSession().gameIsFinished()).isTrue();
    }
}
