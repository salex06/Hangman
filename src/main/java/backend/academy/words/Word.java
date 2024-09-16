package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import java.util.Objects;

/**
 * A Word record stores information about the word
 *
 * @param value    the word itself
 * @param hint     a hint to the word
 * @param category word category
 * @param level    level category
 */
public record Word(String value, String hint, Category category, Level level) {

    @Override public String toString() {
        return value;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Word word = (Word) o;
        return Objects.equals(value, word.value) && Objects.equals(hint, word.hint) && category == word.category
            && level == word.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, hint, category, level);
    }
}
