package backend.academy.game;

import backend.academy.game.implementations.GallowsArtist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


class GallowsArtistTest {
    GallowsArtist gallowsArtist;

    @BeforeEach
    public void setup(){
        gallowsArtist = new GallowsArtist(6);
    }

    public static List<Map.Entry<Integer, String>> data(){
        Map<Integer, String> map = Map.ofEntries(Map.entry(
            0, "______\n|/\t |\n|\n"
            ),
            Map.entry(
               1,  "______\n|/\t |\n|\t о\n"
            ),
            Map.entry(
                2,"______\n|/\t |\n|\t о\n|\t |\n"
            ),
            Map.entry(
                3,"______\n|/\t |\n|\t o\n|\t/|\n"
            ),
            Map.entry(
                4,"______\n|/\t |\n|\t o\n|\t/|\\\n"
            ),
            Map.entry(
                5,"______\n|/\t |\n|\t o\n|\t/|\\\n| \t/\n"
            ),
            Map.entry(
                6,"______\n|/\t |\n|\t o\n|\t/|\\\n| \t/ \\\n"
            )
        );
        return map.entrySet().stream().toList();
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    @DisplayName("Test drawing gallows")
    void testIfNumberOfAttemptsIsEqualsListSize(Map.Entry<Integer, String> data){
        String expected = data.getValue();
        assertEquals(expected, gallowsArtist.getCurrGallowsState(data.getKey()));
    }

}
