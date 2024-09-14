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
@Setter
public class GameSession implements Session {
    private String answer;
    @SuppressWarnings({"all"})
    private int NUMBER_OF_ATTEMPTS;
    @Getter
    private final static int MINIMAL_NUMBER_OF_ATTEMPTS = 6;
    private GameState gameState;
    private ConsoleInteractor consoleInteractor;
    private GallowsArtist gallowsArtist;
    private WordsStorage wordsStorage;

    public GameSession() {
        consoleInteractor = new ConsoleInteractor();
        gallowsArtist = new GallowsArtist();
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
