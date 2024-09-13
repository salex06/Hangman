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

    public static Category randomCategory() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static List<String> getCategoryAsStringList() {
        List<String> categoriesList = new ArrayList<>(SIZE);
        for (Category category : values()) {
            categoriesList.add(category.name());
        }
        return categoriesList;
    }

    public static Category getCategory(String stringOrdinal) {
        if (stringOrdinal.isBlank()) {
            return Category.randomCategory();
        }
        int categoryOrdinal = Integer.parseInt(stringOrdinal);
        if (categoryOrdinal > Category.values().length || categoryOrdinal < 1) {
            //it will be better to throw an Exception
            return Category.randomCategory();
        }
        return Category.values()[categoryOrdinal - 1];
    }
}
