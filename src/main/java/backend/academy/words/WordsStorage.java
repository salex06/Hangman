package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import static backend.academy.words.enums.Category.MUSIC;
import static backend.academy.words.enums.Category.PROGRAMMING;
import static backend.academy.words.enums.Category.SPORT;
import static backend.academy.words.enums.Level.EASY;
import static backend.academy.words.enums.Level.HARD;
import static backend.academy.words.enums.Level.MEDIUM;

@Getter
public class WordsStorage {
    private final Map<CategoryLevel, List<String>> storage;
    private final SecureRandom secureRandom;

    public WordsStorage() {
        storage = new HashMap<>();
        secureRandom = new SecureRandom();
    }

    private void loadStorageWithDemoValues() {
        addWord(MUSIC, EASY, "aria");
        addWord(MUSIC, EASY, "beatles");
        addWord(MUSIC, MEDIUM, "guitar");
        addWord(MUSIC, HARD, "composition");
        addWord(PROGRAMMING, EASY, "java");
        addWord(PROGRAMMING, EASY, "commit");
        addWord(PROGRAMMING, MEDIUM, "solid");
        addWord(PROGRAMMING, HARD, "inheritance");
        addWord(PROGRAMMING, HARD, "smalltalk");
        addWord(SPORT, EASY, "gym");
        addWord(SPORT, EASY, "basketball");
        addWord(SPORT, MEDIUM, "diving");
        addWord(SPORT, HARD, "weightlifter");
    }

    public String getRandomWord(Category category, Level level) {
        if (storage.isEmpty()) {
            loadStorageWithDemoValues();
        }
        CategoryLevel categoryLevel = new CategoryLevel(category, level);
        List<String> curr = storage.get(categoryLevel);
        return curr.get(secureRandom.nextInt(curr.size()));
    }

    public boolean addWord(Category category, Level level, String value) {
        CategoryLevel categoryLevel = new CategoryLevel(category, level);
        List<String> lst = storage.get(categoryLevel);
        if (lst == null) {
            lst = new ArrayList<String>();
            storage.put(categoryLevel, lst);
        }
        if (storage.get(categoryLevel).contains(value)) {
            return false;
        }
        storage.get(categoryLevel).add(value);
        return true;
    }

    //TODO: make reading from a file (csv)
}
