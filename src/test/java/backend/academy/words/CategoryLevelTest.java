package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
class CategoryLevelTest {
    @ParameterizedTest
    @CsvSource({"0, 0", "1, 0 ", "0, 1", "1, 1", "1, 2", "2, 1", "2, 0", "0, 2", "2, 2"})
    void ensureThatInitializationOfCategoryLevelWorks(int category, int level){
        CategoryLevel cl = new CategoryLevel(Category.values()[category], Level.values()[level]);
        assertEquals(category, cl.c().ordinal());
        assertEquals(level, cl.l().ordinal());
    }
}
