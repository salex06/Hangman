package backend.academy.game.implementations;

import backend.academy.game.Session;
import backend.academy.states.GameState;
import backend.academy.states.implementations.ConfigureGameState;
import backend.academy.util.ConsoleInteractor;
import backend.academy.util.GallowsArtist;
import backend.academy.words.Word;
import backend.academy.words.WordsStorage;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

/**
 * The GameSession class implements a Session interface and represents a gaming session.
 * This class contains all fields of information about the current session, the state of
 * the game, other necessary classes such as console interactor, gallows artist, etc.
 */
@Getter
@Setter
public class GameSession implements Session {
    private Word answer;
    @SuppressWarnings({"all"})
    private int NUMBER_OF_ATTEMPTS;
    @Getter
    private final static int MINIMAL_NUMBER_OF_ATTEMPTS = 6;
    private GameState gameState;
    private ConsoleInteractor consoleInteractor;
    private GallowsArtist gallowsArtist;
    private WordsStorage wordsStorage;

    /**
     * The default constructor. Causes the creation of objects of the
     * ConsoleInteractor, GallowsArtist classes, etc.
     */
    public GameSession() {
        consoleInteractor = new ConsoleInteractor();
        gallowsArtist = new GallowsArtist();
        gameState = new ConfigureGameState();
        wordsStorage = new WordsStorage();
    }

    /**
     * The method that starts the game session. Handling exceptions for incorrect input
     * and other errors.
     * @throws IOException if an input/output error has occurred
     */
    public void start() throws IOException {
        try {
            while (!gameState.gameIsFinished()) {
                try {
                    gameState.processState(this);
                } catch (IllegalArgumentException e) {
                    consoleInteractor.clearConsole();
                    consoleInteractor.writeMessage("Incorrect data: " + e.getMessage() + "\n");
                }
            }
        } catch (Exception e) {
            consoleInteractor.clearConsole();
            consoleInteractor.writeMessage("Unknown error: " + e.getMessage() + "\n");
        }
    }
}
