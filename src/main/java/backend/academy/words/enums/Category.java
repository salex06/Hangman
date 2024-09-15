package backend.academy.words.enums;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Category {
    PROGRAMMING, SPORT, MUSIC;

    private static final List<Category> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Select random Category
     * @return random Category type object from the enumeration
     */
    public static Category randomCategory() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    /**
     * Convert Category values to String values
     * @return categoriesList - the List of Strings (names of Categories)
     */
    public static List<String> getCategoryAsStringList() {
        List<String> categoriesList = new ArrayList<>(SIZE);
        for (Category category : values()) {
            categoriesList.add(category.name());
        }
        return categoriesList;
    }

    /**
     * Select category by its ordinal
     * @throws IllegalArgumentException if stringOrdinal is a number, but it's either not positive or
     * bigger than the number of the values in enumeration
     * @param stringOrdinal number of the category in the enumeration
     * @return random Category type object if stringOrdinal is blank;
     * Category type object if stringOrdinal is a correct ordinal
     */
    public static Category getCategory(String stringOrdinal) {
        if (stringOrdinal.isBlank()) {
            return Category.randomCategory();
        }
        int categoryOrdinal = Integer.parseInt(stringOrdinal);
        if (categoryOrdinal > Category.values().length || categoryOrdinal < 1) {
            throw new IllegalArgumentException(
                "Требуется число (или enter для случайной категории) от " + 1 + " до " + SIZE);
        }
        return Category.values()[categoryOrdinal - 1];
    }
}
