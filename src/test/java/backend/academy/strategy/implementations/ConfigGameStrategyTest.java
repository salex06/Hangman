package backend.academy.strategy.implementations;

import backend.academy.game.implementations.GameOrchestrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ConfigGameStrategyTest {
    GameOrchestrator gameOrchestrator;

    @BeforeEach
    void setup() {
        gameOrchestrator = new GameOrchestrator();
        gameOrchestrator.gameStrategy(new ConfigGameStrategy());
    }

    @ParameterizedTest
    @DisplayName("Ensure number of attempts is initializeed correctly")
    @ValueSource(ints = {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16})
    void ensureNumberOfAttemptsIsInitializedCorrectly(int numberOfAttempts) {
        gameOrchestrator.gameStrategy()
            .processStrategy(String.valueOf(numberOfAttempts), gameOrchestrator.gameSession());

        assertEquals(numberOfAttempts, gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS());
    }

    @ParameterizedTest
    @DisplayName("Ensure the method throws an IllegalArgumentException")
    @ValueSource(strings = {"-1", "0", "1", "2", "3"})
    void ensureTheProcessStrategyThrowTheIllegalArgException(String numberOfAttempts) {
        assertThrows(IllegalArgumentException.class,
            () -> gameOrchestrator.gameStrategy().processStrategy(numberOfAttempts, gameOrchestrator.gameSession()));
    }

    @ParameterizedTest
    @DisplayName("Ensure the method throws a NumberFormatException")
    @ValueSource(strings = {" ", "\n", "\t", "A", "ABCD"})
    void ensureTheProcessStrategyThrowTheException(String numberOfAttempts) {
        assertThrows(NumberFormatException.class,
            () -> gameOrchestrator.gameStrategy().processStrategy(numberOfAttempts, gameOrchestrator.gameSession()));
    }

    private static Stream<String> answersAndHints() {
        return Stream.of(
            "test1 hint1",
            "test2 hint2",
            "test3 hint3"
        );
    }

    @ParameterizedTest
    @DisplayName("Ensure the answer and hint are initialized correctly")
    @MethodSource("answersAndHints")
    void ensureTheAnswerAndHintAreInitializedCorrectly(String currentString) {
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(10); //to enter the else if block

        String[] splitted = currentString.split(" ");
        String expectedAnswer = splitted[0];
        String expectedHint = splitted[1];
        StringBuilder expectedStringBuilder = new StringBuilder().append("-".repeat(expectedAnswer.length()));

        gameOrchestrator.gameStrategy().processStrategy(currentString, gameOrchestrator.gameSession());

        assertThat(gameOrchestrator.gameSession().answer()).isEqualTo(expectedAnswer);
        assertThat(gameOrchestrator.gameSession().hint()).isEqualTo(expectedHint);
        assertThat(gameOrchestrator.gameSession().attemptStringBuilder().toString()).isEqualTo(
            expectedStringBuilder.toString());
    }

    private static Stream<String> incorrectAnswersAndHints() {
        return Stream.of(
            "test1hint1",
            "",
            " ",
            "test2 hint2 somethingElse3"
        );
    }

    @ParameterizedTest
    @DisplayName("Ensure the method throws an Exception")
    @MethodSource("incorrectAnswersAndHints")
    void ensureThrowTheExceptionIfTheAnswerAndHintIsIncorrect(String currentString) {
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(10); //to enter the else if block

        assertThrows(RuntimeException.class,
            () -> gameOrchestrator.gameStrategy().processStrategy(currentString, gameOrchestrator.gameSession()));
    }

}
