package backend.academy.game.implementations;

import backend.academy.game.interfaces.Session;
import backend.academy.states.implementations.ConfigureGameState;
import backend.academy.states.interfaces.GameState;
import backend.academy.util.ConsoleInteractor;
import backend.academy.words.WordsStorage;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GameSession implements Session {
    @Setter
    private String answer;
    @SuppressWarnings({"all"})
    private final int NUMBER_OF_ATTEMPTS = 6;
    @Setter
    private GameState gameState;
    private ConsoleInteractor consoleInteractor;
    private GallowsArtist gallowsArtist;
    private WordsStorage wordsStorage;

    public GameSession() {
        consoleInteractor = new ConsoleInteractor();
        gallowsArtist = new GallowsArtist(NUMBER_OF_ATTEMPTS);
        gameState = new ConfigureGameState();
        wordsStorage = new WordsStorage();
    }

    public void start() throws IOException {
        while (true) {
            gameState.processState(this);
            if (gameState.gameIsFinished()) {
                break;
            }
        }
    }
}
