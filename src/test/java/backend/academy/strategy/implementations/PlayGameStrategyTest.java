package backend.academy.strategy.implementations;

import backend.academy.game.implementations.GameOrchestrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayGameStrategyTest {
    GameOrchestrator gameOrchestrator;

    @BeforeEach
    void setup(){
        gameOrchestrator = new GameOrchestrator();
        gameOrchestrator.gameStrategy(new PlayGameStrategy());
    }

    @Test
    @DisplayName("Ensure that hint will be provided if user asks")
    void ensureHintIsProvided(){
        gameOrchestrator.gameStrategy().processStrategy("-", gameOrchestrator.gameSession());

        assertThat(gameOrchestrator.gameSession().needHint()).isTrue();
    }

    @Test
    @DisplayName("Ensure string builder updates correctly")
    void ensureStringBuilderUpdatesCorrectly(){
        gameOrchestrator.gameSession().answer("demo");
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(10);
        gameOrchestrator.gameSession().attemptStringBuilder(new StringBuilder().append("-".repeat(4)));

        assertThat(gameOrchestrator.gameSession().attemptStringBuilder().toString()).isEqualTo("----");
        gameOrchestrator.gameStrategy().processStrategy("d", gameOrchestrator.gameSession());
        assertThat(gameOrchestrator.gameSession().attemptStringBuilder().toString()).isEqualTo("d---");
        gameOrchestrator.gameStrategy().processStrategy("q", gameOrchestrator.gameSession());
        assertThat(gameOrchestrator.gameSession().attemptStringBuilder().toString()).isEqualTo("d---");
        gameOrchestrator.gameStrategy().processStrategy("E", gameOrchestrator.gameSession());
        assertThat(gameOrchestrator.gameSession().attemptStringBuilder().toString()).isEqualTo("de--");
        gameOrchestrator.gameStrategy().processStrategy("o", gameOrchestrator.gameSession());
        assertThat(gameOrchestrator.gameSession().attemptStringBuilder().toString()).isEqualTo("de-o");
        gameOrchestrator.gameStrategy().processStrategy("", gameOrchestrator.gameSession());
        assertThat(gameOrchestrator.gameSession().attemptStringBuilder().toString()).isEqualTo("de-o");
        gameOrchestrator.gameStrategy().processStrategy("m", gameOrchestrator.gameSession());
        assertThat(gameOrchestrator.gameSession().attemptStringBuilder().toString()).isEqualTo("demo");
    }

    @Test
    @DisplayName("Ensure that gameResult (win) is initialized correctly")
    void ensureSuccessfulGameResultIsInitializedCorrectly(){
        gameOrchestrator.gameSession().answer("tests");
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(6);
        gameOrchestrator.gameSession().attemptStringBuilder(new StringBuilder().append("-".repeat(5)));

        gameOrchestrator.gameStrategy().processStrategy("t", gameOrchestrator.gameSession());
        gameOrchestrator.gameStrategy().processStrategy("e", gameOrchestrator.gameSession());
        gameOrchestrator.gameStrategy().processStrategy("s", gameOrchestrator.gameSession());

        assertThat(gameOrchestrator.gameSession().gameResult()).isEqualTo("win");
    }

    @Test
    @DisplayName("Ensure that gameResult (lose) is initialized correctly")
    void ensureUnsuccessfulGameResultIsInitializedCorrectly(){
        gameOrchestrator.gameSession().answer("tests");
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(6);
        gameOrchestrator.gameSession().attemptStringBuilder(new StringBuilder().append("-".repeat(5)));

        gameOrchestrator.gameStrategy().processStrategy("A", gameOrchestrator.gameSession());
        gameOrchestrator.gameStrategy().processStrategy("b", gameOrchestrator.gameSession());
        gameOrchestrator.gameStrategy().processStrategy("C", gameOrchestrator.gameSession());
        gameOrchestrator.gameStrategy().processStrategy("d", gameOrchestrator.gameSession());
        gameOrchestrator.gameStrategy().processStrategy("F", gameOrchestrator.gameSession());
        gameOrchestrator.gameStrategy().processStrategy("g", gameOrchestrator.gameSession());

        assertThat(gameOrchestrator.gameSession().gameResult()).isEqualTo("lose");
    }

    @ParameterizedTest
    @DisplayName("Ensure that method will do nothing if user enters the incorrect input")
    @ValueSource(strings = {"", " ", "\n", "\t", "AA", "aa"})
    void ensureMethodWorksCorrectlyIfUserEntersIncorrectData(String input){
        gameOrchestrator.gameSession().answer("tests");
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(6);
        gameOrchestrator.gameSession().attemptStringBuilder(new StringBuilder().append("-".repeat(5)));

        StringBuilder expectedStringBuilder = new StringBuilder().append("-".repeat(5));
        int expectedAttemptNumber = 0;
        gameOrchestrator.gameStrategy().processStrategy(input, gameOrchestrator.gameSession());

        assertThat(gameOrchestrator.gameSession().attemptStringBuilder().toString()).isEqualTo(expectedStringBuilder.toString());
        assertThat(gameOrchestrator.gameSession().currentAttemptNumber()).isEqualTo(expectedAttemptNumber);
    }
}
