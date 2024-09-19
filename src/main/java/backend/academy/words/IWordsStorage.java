package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;

/**
 * The IWordsStorage repository provides operations for getting and adding
 * random words by categories and levels from the repository
 */
public interface IWordsStorage {
    /**
     * Returns a Word type object - a randomly selected word by level and category
     * from the storage
     *
     * @param category The category (PROGRAMMING, SPORT, etc.) of the word
     * @param level    The level (EASY, MEDIUM, etc.) of the word
     * @return a Word type object
     */
    Word getRandomWord(Category category, Level level);

    /**
     * Add the Word object to the storage
     * @param category Category (PROGRAMMING, SPORT, etc.) of the Word
     * @param level    Level (EASY, MEDIUM, etc.) of the Word
     * @param value    Word type object
     * @return boolean type value - the result of adding (true - adding completed, false - adding failed)
     */
    boolean addWord(Category category, Level level, Word value);
}
