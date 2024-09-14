package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
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
}
