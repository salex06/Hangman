package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Word {
    private final String value;
    private final String hint;
    private final Category category;
    private final Level level;

    public Word(String value, String hint, Category category, Level level) {
        this.value = value;
        this.hint = hint;
        this.category = category;
        this.level = level;
    }

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
