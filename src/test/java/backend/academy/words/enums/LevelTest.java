package backend.academy.words.enums;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    @ParameterizedTest
    @CsvSource({"1, EASY", "2, MEDIUM", "3, HARD"})
    void testGetLevelIfActualStringIsCorrect(String actualString, Level expectedLevel) {
        Level actual = Level.getLevel(actualString);
        assertEquals(expectedLevel, actual);
    }


    @ParameterizedTest
    @ValueSource(strings = {"4", "0", "-1", "", " ", "\n", "\t"})
    void testGetRandomLevelIfActualStringIsIncorrect(String actualString){
        assertThat(Level.getLevel(actualString)).isInstanceOf(Level.class);
    }
}
