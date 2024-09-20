package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class WordTest {
    @ParameterizedTest
    @CsvSource({"word1, hint1, PROGRAMMING, EASY", "word2, hint2, SPORT, HARD", "word3, hint3, MUSIC, MEDIUM"})
    void ensureThatInitializationOfWordWorksCorrectly(String word, String hint, Category category, Level level) {
        Word _word = new Word(word, hint, category, level);
        assertEquals(word, _word.value());
        assertEquals(hint, _word.hint());
        assertEquals(category, _word.category());
        assertEquals(level, _word.level());
    }

    @Test
    @DisplayName("Ensure the toString method works correctly")
    void ensureToStringWorksCorrectly(){
        Word word = new Word("word", "hint", Category.MUSIC, Level.EASY);
        assertEquals("word", word.toString());
    }
}
