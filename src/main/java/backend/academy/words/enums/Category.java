package backend.academy.words.enums;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The Category enumeration stores information about available categories and
 * several methods to get these types of categories in the right form
 */
public enum Category {
    PROGRAMMING, SPORT, MUSIC;

    private static final List<Category> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Select random Category
     *
     * @return random Category type object from the enumeration
     */
    public static Category randomCategory() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    /**
     * Convert Category values to String values
     *
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
     * Select category by its ordinal number
     *
     * @param stringOrdinal number of the category in the enumeration
     * @return Optional type object (Optional.empty() if the stringOrdinal is incorrect)
     * @throws IllegalArgumentException if stringOrdinal is a number, but it's either not positive or
     *                                  bigger than the number of the values in enumeration
     */
    public static Optional<Category> getCategory(String stringOrdinal) {
        if (stringOrdinal.isBlank()) {
            return Optional.empty();
        }
        int categoryOrdinal = Integer.parseInt(stringOrdinal);
        if (categoryOrdinal > Category.values().length || categoryOrdinal < 1) {
            throw new IllegalArgumentException(
                "A number is required (or enter for a random category) from " + 1 + " to " + SIZE);
        }
        return Optional.of(Category.values()[categoryOrdinal - 1]);
    }
}
