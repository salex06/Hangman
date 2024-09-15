package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import java.util.Objects;

public record CategoryLevel(Category c, Level l) {

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryLevel that = (CategoryLevel) o;
        return c == that.c && l == that.l;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, l);
    }
}
