package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import backend.academy.words.implementations.WordsStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

class WordsStorageTest {
    WordsStorage wordsStorage;

    @BeforeEach
    void setup(){
        wordsStorage = new WordsStorage();
    }

    private static List<Map.Entry<Word, CategoryLevel>> demoWordsSet(){
        Map<Word, CategoryLevel> map = Map.ofEntries(
            Map.entry(new Word("musicEasy1", "hint", Category.MUSIC, Level.EASY), new CategoryLevel(Category.MUSIC, Level.EASY)),
            Map.entry(new Word("musicEasy2", "hint", Category.MUSIC, Level.EASY), new CategoryLevel(Category.MUSIC, Level.EASY)),
            Map.entry(new Word("musicMedium1", "hint", Category.MUSIC, Level.MEDIUM), new CategoryLevel(Category.MUSIC, Level.MEDIUM)),
            Map.entry(new Word("musicMedium2", "hint", Category.MUSIC, Level.MEDIUM), new CategoryLevel(Category.MUSIC, Level.MEDIUM)),
            Map.entry(new Word("musicHard1", "hint", Category.MUSIC, Level.HARD), new CategoryLevel(Category.MUSIC, Level.HARD)),
            Map.entry(new Word("musicHard2", "hint", Category.MUSIC, Level.HARD), new CategoryLevel(Category.MUSIC, Level.HARD)),
            Map.entry(new Word("programmingEasy1", "hint", Category.PROGRAMMING, Level.EASY), new CategoryLevel(Category.PROGRAMMING, Level.EASY)),
            Map.entry(new Word("programmingEasy2", "hint", Category.PROGRAMMING, Level.EASY), new CategoryLevel(Category.PROGRAMMING, Level.EASY)),
            Map.entry(new Word("programmingMedium1", "hint", Category.PROGRAMMING, Level.MEDIUM), new CategoryLevel(Category.PROGRAMMING, Level.MEDIUM)),
            Map.entry(new Word("programmingMedium2", "hint", Category.PROGRAMMING, Level.MEDIUM), new CategoryLevel(Category.PROGRAMMING, Level.MEDIUM)),
            Map.entry(new Word("programmingHard1", "hint", Category.PROGRAMMING, Level.HARD), new CategoryLevel(Category.PROGRAMMING, Level.HARD)),
            Map.entry(new Word("programmingHard2", "hint", Category.PROGRAMMING, Level.HARD),new CategoryLevel(Category.PROGRAMMING, Level.HARD)),
            Map.entry(new Word("sportEasy1", "hint", Category.SPORT, Level.EASY), new CategoryLevel(Category.SPORT, Level.EASY)),
            Map.entry(new Word("sportEasy2", "hint", Category.SPORT, Level.EASY), new CategoryLevel(Category.SPORT, Level.EASY)),
            Map.entry(new Word("sportMedium1", "hint", Category.SPORT, Level.MEDIUM), new CategoryLevel(Category.SPORT, Level.MEDIUM)),
            Map.entry(new Word("sportMedium2", "hint", Category.SPORT, Level.MEDIUM), new CategoryLevel(Category.SPORT, Level.MEDIUM)),
            Map.entry(new Word("sportHard1", "hint", Category.SPORT, Level.HARD), new CategoryLevel(Category.SPORT, Level.HARD)),
            Map.entry(new Word("sportHard2", "hint", Category.SPORT, Level.HARD), new CategoryLevel(Category.SPORT, Level.EASY))
        );
        return map.entrySet().stream().toList();
    }

    @ParameterizedTest
    @DisplayName("Check adding words to storage")
    @MethodSource("demoWordsSet")
    void testAddWordToStorage(Map.Entry<Word, CategoryLevel> current) {
        Category c = current.getValue().c();
        Level l = current.getValue().l();
        Word value = current.getKey();

        wordsStorage.addWord(c, l, value);

        assertThat(wordsStorage.storage().get(current.getValue())).contains(value);
    }


    private static List<Word> demoWordsSet2(){
        List<Word> words = List.of(
            new Word("notAdded1", "hint", Category.MUSIC, Level.EASY),
            new Word("notAdded2", "hint", Category.PROGRAMMING, Level.MEDIUM),
            new Word("notAdded3", "hint", Category.SPORT, Level.HARD)
        );
        return words;
    }

    @ParameterizedTest
    @DisplayName("Storage doesn't contain the words")
    @MethodSource("demoWordsSet2")
    void testIfStorageHaventTheValues(Word currentValue){
        assertThat(wordsStorage.storage().containsValue(currentValue)).isFalse();
    }

}
