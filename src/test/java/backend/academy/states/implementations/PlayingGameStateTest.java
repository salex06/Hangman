package backend.academy.states.implementations;

import backend.academy.game.implementations.GameSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayingGameStateTest {
    final int NUMBER_OF_ATTEMPTS = 6;
    PlayingGameState pgs;

    @ParameterizedTest
    @DisplayName("Check initialization")
    @ValueSource(ints = {1,2,3,4,5,6,7})
    void ensureInitializationIsSuccessful(int stringLength){
        pgs = new PlayingGameState(stringLength);
        String expected = "-".repeat(Math.max(0, stringLength));
        assertEquals(expected, pgs.attemptStringBuilder().toString());
    }

    @ParameterizedTest
    @DisplayName("Ensure current and answer are equal")
    @CsvSource({"basketball, basketball", "smalltalk, smalltalk", "gym, gym"})
    void ensureCurrentAndAnswerAreEqual(String actual, String expected){
        pgs = new PlayingGameState(expected.length());
        pgs.attemptStringBuilder(new StringBuilder(actual));

        boolean checkResult = pgs.compareAnswerToCurrent(expected);

        assertThat(checkResult).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Ensure current and answer are not equal")
    @CsvSource({"b-sketb---, basketball", "---------, smalltalk", "-ym, gym"})
    void ensureCurrentAndAnswerAreNotEqual(String actual, String expected){
        pgs = new PlayingGameState(expected.length());
        pgs.attemptStringBuilder(new StringBuilder(actual));

        boolean checkResult = pgs.compareAnswerToCurrent(expected);

        assertThat(checkResult).isFalse();
    }

    @Test
    @DisplayName("Ensure updating StringBuilder works")
    void ensureUpdatingStringBuilderWorks(){
        String answer = "basketball";
        pgs = new PlayingGameState(answer.length());

        boolean shouldBeTrue = pgs.updateAttemptStringBuilder(answer, 'l');

        assertThat(shouldBeTrue).isTrue();

        boolean ShouldBeFalse = pgs.updateAttemptStringBuilder(answer, 'm');

        assertThat(ShouldBeFalse).isFalse();
    }



    @Test
    @DisplayName("Ensure method manageStateData works correctly when no user mistakes")
    void ensureManageStateDataWorksCorrectlyWhenNoUserMistakesAndUserWin(){
        String answer = "gym";
        pgs = new PlayingGameState(answer.length());
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "g");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(0);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "y");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(0);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "m");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(0);
        assertThat(pgs.gameResult()).isEqualTo(true);
    }

    @Test
    @DisplayName("Ensure method manageStateData works correctly when user win with some mistakes")
    void ensureManageStateDataWorksCorrectlyWhenSomeUserMistakesAndUserWin(){
        String answer = "demotest";
        pgs = new PlayingGameState(answer.length());
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "c");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(1);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "e");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(1);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "m");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(1);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "t");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(1);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "d");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(1);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "o");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(1);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "k");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(2);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "s");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(2);
        assertThat(pgs.gameResult()).isEqualTo(true);
    }

    @Test
    @DisplayName("Ensure method manageStateData works correctly if user failed")
    void ensureManageStateDataWorksCorrectlyIfUserFailed(){
        String answer = "gym";
        pgs = new PlayingGameState(answer.length());
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "c");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(1);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "e");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(2);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "m");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(2);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "t");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(3);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "d");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(4);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "o");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(5);
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, "k");
        assertThat(pgs.currentAttemptNumber()).isEqualTo(6);
        assertThat(pgs.gameResult()).isEqualTo(false);
    }

    @Test
    @DisplayName("Ensure manageStateData works if user asked for a hint")
    void ensureManageStateDataWorksIfUserAskedForAHint(){
        String hintSymb = "-";
        String answer = "gym";
        pgs = new PlayingGameState(answer.length());
        assertThat(pgs.needHint()).isFalse();
        pgs.manageStateData(answer, NUMBER_OF_ATTEMPTS, hintSymb);
        assertThat(pgs.needHint()).isTrue();
    }
}
