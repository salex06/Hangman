package backend.academy.words.enums;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    @ParameterizedTest
    @CsvSource({"1, EASY", "2, MEDIUM", "3, HARD"})
    void testGetLevelIfActualStringIsCorrect(String actualString, Level expectedLevel) {
        Optional<Level> actual = Level.getLevel(actualString);

        if (actual.isPresent()) {
            assertEquals(expectedLevel, actual.orElseThrow());
        } else {
            assert false;
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"4", "0", "-1"})
    void testGetRandomLevelIfLevelNumberIsBiggerOrLowerThanItMust(String actualString) {
        assertThrows(IllegalArgumentException.class, () -> Level.getLevel(actualString));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void testGetRandomLevelIfActualStringIsIncorrect(String actualString) {
        assertEquals(Optional.empty(), Level.getLevel(actualString));
    }

}
