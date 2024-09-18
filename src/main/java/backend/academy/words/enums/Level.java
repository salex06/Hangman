package backend.academy.words.enums;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The Level enumeration stores information about available levels and
 * several methods to get these types of levels in the right form
 */
public enum Level {
    EASY, MEDIUM, HARD;

    private static final List<Level> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final SecureRandom RANDOM = new SecureRandom();

    public static Level randomLevel() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    /**
     * Convert Level objects to String objects
     *
     * @return List of String type objects - names of level constants
     */
    public static List<String> getLevelAsStringList() {
        List<String> levelsList = new ArrayList<>(SIZE);
        for (Level level : values()) {
            levelsList.add(level.name());
        }
        return levelsList;
    }

    /**
     * Allow get Level type object by its ordinal number
     *
     * @param stringOrdinal the constant number of the enumeration in order
     * @return Optional type object (Optional.empty() if the stringOrdinal is incorrect)
     * @throws IllegalArgumentException if user entered the wrong number of the constant of the enumeration
     */
    public static Optional<Level> getLevel(String stringOrdinal) {
        if (stringOrdinal.isBlank()) {
            return Optional.empty();
        }
        int levelOrdinal = Integer.parseInt(stringOrdinal);
        if (levelOrdinal > Level.values().length || levelOrdinal < 1) {
            throw new IllegalArgumentException(
                "A number is required (or enter for a random level) from " + 1 + " to " + SIZE);
        }
        return Optional.of(Level.values()[levelOrdinal - 1]);
    }
}
