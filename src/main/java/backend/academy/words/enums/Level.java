package backend.academy.words.enums;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Level {
    EASY, MEDIUM, HARD;

    private static final List<Level> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final SecureRandom RANDOM = new SecureRandom();

    public static Level randomLevel() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static List<String> getLevelAsStringList() {
        List<String> levelsList = new ArrayList<>(SIZE);
        for (Level level : values()) {
            levelsList.add(level.name());
        }
        return levelsList;
    }

    public static Level getLevel(String stringOrdinal) {
        if (stringOrdinal.isBlank()) {
            return Level.randomLevel();
        }
        int levelOrdinal = Integer.parseInt(stringOrdinal);
        if (levelOrdinal > Level.values().length || levelOrdinal < 1) {
            //it will be better to throw an Exception
            return Level.randomLevel();
        }
        return Level.values()[levelOrdinal - 1];
    }
}
