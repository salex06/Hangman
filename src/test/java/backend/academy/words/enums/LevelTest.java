package backend.academy.words.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
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
    @ValueSource(strings = {"4", "0", "-1"})
    void testGetRandomLevelIfLevelNumberIsBiggerOrLowerThanItMust(String actualString) {
        assertThrows(IllegalArgumentException.class, () -> Level.getLevel(actualString));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void testGetRandomLevelIfActualStringIsIncorrect(String actualString) {
        assertThat(Level.getLevel(actualString)).isInstanceOf(Level.class);
    }

    @Test
    @DisplayName("Ensure getting a list of the levels works")
    void ensureGettingLevelsListWorks() {
        List<String> expected = List.of("EASY", "MEDIUM", "HARD");

        List<String> actual = Level.getLevelAsStringList();

        assertEquals(expected, actual);
    }
}
