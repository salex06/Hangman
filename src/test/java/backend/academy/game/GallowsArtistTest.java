package backend.academy.game;

import backend.academy.util.implementations.GallowsArtist;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GallowsArtistTest {
    public static List<Map.Entry<Integer, String>> data() {
        Map<Integer, String> map = Map.ofEntries(Map.entry(
                0, "______\n|/ |\n|   \n|   \n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                1, "______\n|/ |\n|  o\n|   \n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                2, "______\n|/ |\n|  o\n|  |\n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                3, "______\n|/ |\n|  o\n| /|\n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                4, "______\n|/ |\n|  o\n| /|\\\n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                5, "______\n|/ |\n|  o\n| /|\\\n| /\n|   \n|   \n|   \n"
            ),
            Map.entry(
                6, "______\n|/ |\n|  o\n| /|\\\n| / \\\n|   \n|   \n|   \n"
            )
        );
        return map.entrySet().stream().toList();
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    @DisplayName("Test drawing gallows")
    void testIfNumberOfAttemptsIsEqualsListSize(Map.Entry<Integer, String> data) {
        int numberOfAttempts = 6;
        GallowsArtist gallowsArtist = new GallowsArtist();
        String expected = data.getValue();
        assertEquals(expected, gallowsArtist.getCurrGallowsState(numberOfAttempts, data.getKey()));
    }

    public static List<Map.Entry<Integer, String>> data2() {
        Map<Integer, String> map = Map.ofEntries(Map.entry(
                0, "______\n|/ |\n|   \n|   \n|   \n|   \n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                1, "______\n|/ |\n|  |\n|   \n|   \n|   \n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                2, "______\n|/ |\n|  |\n|  |\n|   \n|   \n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                3, "______\n|/ |\n|  |\n|  |\n|  o\n|   \n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                4, "______\n|/ |\n|  |\n|  |\n|  o\n|  |\n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                5, "______\n|/ |\n|  |\n|  |\n|  o\n| /|\n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                6, "______\n|/ |\n|  |\n|  |\n|  o\n| /|\\\n|   \n|   \n|   \n|   \n"
            ),
            Map.entry(
                7, "______\n|/ |\n|  |\n|  |\n|  o\n| /|\\\n| /\n|   \n|   \n|   \n"
            ),
            Map.entry(
                8, "______\n|/ |\n|  |\n|  |\n|  o\n| /|\\\n| / \\\n|   \n|   \n|   \n"
            )
        );
        return map.entrySet().stream().toList();
    }

    @ParameterizedTest
    @MethodSource(value = "data2")
    @DisplayName("Test drawing gallows (numberOfAttempts is bigger than list size)")
    void testIfNumberOfAttemptsIsBiggerThanListSize(Map.Entry<Integer, String> data) {
        int numberOfAttempts = 8;
        GallowsArtist gallowsArtist = new GallowsArtist();
        String expected = data.getValue();
        assertEquals(expected, gallowsArtist.getCurrGallowsState(numberOfAttempts, data.getKey()));
    }

}
