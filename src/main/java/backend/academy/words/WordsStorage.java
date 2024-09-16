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

/**
 * The WordsStorage keeps a dictionary of the words and methods
 * by which you can get and add different words
 */
@Getter
public class WordsStorage {
    private final Map<CategoryLevel, List<Word>> storage;
    private final SecureRandom secureRandom;

    public WordsStorage() {
        storage = new HashMap<>();
        secureRandom = new SecureRandom();
    }

    private void loadStorageWithDemoValues() {
        addWord(MUSIC, EASY, new Word("aria", "Valeriy Kipelov", MUSIC, EASY));
        addWord(MUSIC, EASY, new Word("beatles", "John Lennon", MUSIC, EASY));
        addWord(MUSIC, MEDIUM, new Word("guitar", "Fender", MUSIC, MEDIUM));
        addWord(MUSIC, HARD, new Word("composition", "A piece of music", MUSIC, HARD));
        addWord(PROGRAMMING, EASY, new Word("java", "The best language", PROGRAMMING, EASY));
        addWord(PROGRAMMING, EASY, new Word("commit", "The basic entity of Git", PROGRAMMING, EASY));
        addWord(PROGRAMMING, MEDIUM, new Word("solid", "The set of principles of programming", PROGRAMMING, MEDIUM));
        addWord(PROGRAMMING, HARD, new Word("inheritance", "The principle of OOP", PROGRAMMING, HARD));
        addWord(PROGRAMMING, HARD, new Word("smalltalk", "The lang created by Alan Kay", PROGRAMMING, HARD));
        addWord(SPORT, EASY, new Word("gym", "A place for training", SPORT, EASY));
        addWord(SPORT, EASY, new Word("basketball", "The most popular sport in the USA", SPORT, EASY));
        addWord(SPORT, MEDIUM, new Word("diving", "The water sport", SPORT, MEDIUM));
        addWord(SPORT, HARD, new Word("weightlifter", "The man who lifts the barbell", SPORT, HARD));
    }

    /**
     * Returns a Word type object - a randomly selected word by level and category
     *
     * @param category The category (PROGRAMMING, SPORT, etc.) of the word
     * @param level    The level (EASY, MEDIUM, etc.) of the word
     * @return a Word type object
     */
    public Word getRandomWord(Category category, Level level) {
        if (storage.isEmpty()) {
            loadStorageWithDemoValues();
        }
        CategoryLevel categoryLevel = new CategoryLevel(category, level);
        List<Word> curr = storage.get(categoryLevel);
        return curr.get(secureRandom.nextInt(curr.size()));
    }

    /**
     * Add the Word object to the dictionary
     *
     * @param category Category (PROGRAMMING, SPORT, etc.) of the Word
     * @param level    Level (EASY, MEDIUM, etc.) of the Word
     * @param value    Word type object
     * @return boolean type value - the result of adding (true - adding completed, false - adding failed)
     */
    public boolean addWord(Category category, Level level, Word value) {
        CategoryLevel categoryLevel = new CategoryLevel(category, level);
        List<Word> lst = storage.get(categoryLevel);
        if (lst == null) {
            lst = new ArrayList<>();
            storage.put(categoryLevel, lst);
        }
        if (storage.get(categoryLevel).contains(value)) {
            return false;
        }
        storage.get(categoryLevel).add(value);
        return true;
    }

}
