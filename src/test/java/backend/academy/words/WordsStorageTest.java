package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class WordsStorageTest {
    WordsStorage wordsStorage;

    @BeforeEach
    void setup(){
        wordsStorage = new WordsStorage();
    }

    private static List<Map.Entry<String, CategoryLevel>> demoWordsSet(){
        Map<String, CategoryLevel> map = Map.ofEntries(
            Map.entry("musicEasy1", new CategoryLevel(Category.MUSIC, Level.EASY)),
            Map.entry("musicEasy2", new CategoryLevel(Category.MUSIC, Level.EASY)),
            Map.entry("musicMedium1", new CategoryLevel(Category.MUSIC, Level.MEDIUM)),
            Map.entry("musicMedium2", new CategoryLevel(Category.MUSIC, Level.MEDIUM)),
            Map.entry("musicHard1", new CategoryLevel(Category.MUSIC, Level.HARD)),
            Map.entry("musicHard2", new CategoryLevel(Category.MUSIC, Level.HARD)),
            Map.entry("programmingEasy1", new CategoryLevel(Category.PROGRAMMING, Level.EASY)),
            Map.entry("programmingEasy2", new CategoryLevel(Category.PROGRAMMING, Level.EASY)),
            Map.entry("programmingMedium1", new CategoryLevel(Category.PROGRAMMING, Level.MEDIUM)),
            Map.entry("programmingMedium2", new CategoryLevel(Category.PROGRAMMING, Level.MEDIUM)),
            Map.entry("programmingHard1", new CategoryLevel(Category.PROGRAMMING, Level.HARD)),
            Map.entry("programmingHard2",new CategoryLevel(Category.PROGRAMMING, Level.HARD)),
            Map.entry("sportEasy1", new CategoryLevel(Category.SPORT, Level.EASY)),
            Map.entry("sportEasy2", new CategoryLevel(Category.SPORT, Level.EASY)),
            Map.entry("sportMedium1", new CategoryLevel(Category.SPORT, Level.MEDIUM)),
            Map.entry("sportMedium2", new CategoryLevel(Category.SPORT, Level.MEDIUM)),
            Map.entry("sportHard1", new CategoryLevel(Category.SPORT, Level.HARD)),
            Map.entry("sportHard2", new CategoryLevel(Category.SPORT, Level.EASY))
        );
        return map.entrySet().stream().toList();
    }

    @ParameterizedTest
    @DisplayName("Check adding words to storage")
    @MethodSource("demoWordsSet")
    void testAddWordToStorage(Map.Entry<String, CategoryLevel> current) {
        Category c = current.getValue().c;
        Level l = current.getValue().l;;
        String value = current.getKey();

        wordsStorage.addWord(c, l, value);

        assertThat(wordsStorage.storage().get(current.getValue())).contains(value);
    }

    @ParameterizedTest
    @DisplayName("Storage doesn't contain the words")
    @ValueSource(strings = {"notAdded1", "notAdded2", "notAdded3"})
    void testIfStorageHaventTheValues(String currentValue){
        assertThat(wordsStorage.storage().containsValue(currentValue)).isFalse();
    }

}
