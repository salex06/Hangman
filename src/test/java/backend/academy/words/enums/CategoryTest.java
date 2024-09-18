package backend.academy.words.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @ParameterizedTest
    @CsvSource({"1, PROGRAMMING", "2, SPORT", "3, MUSIC"})
    void testGetCategoryIfActualStringIsCorrect(String actualString, Category expectedCategory) {
        Optional<Category> actual = Category.getCategory(actualString);
        if(actual.isPresent()) {
            assertEquals(expectedCategory, actual.orElseThrow());
        }else{
            assert false;
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"4", "0", "-1"})
    void testGetRandomCategoryIfCategoryNumberIsBiggerOrLowerThanItMust(String actualString) {
        assertThrows(IllegalArgumentException.class, () -> Category.getCategory(actualString));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void testGetRandomCategoryIfActualStringIsIncorrect(String actualString) {
        assertEquals(Optional.empty(), Category.getCategory(actualString));
    }

    @Test
    @DisplayName("Ensure getting a list of the categories works")
    void ensureGettingCategoriesListWorks() {
        List<String> expected = List.of("PROGRAMMING", "SPORT", "MUSIC");

        List<String> actual = Category.getCategoryAsStringList();

        assertEquals(expected, actual);
    }
}
