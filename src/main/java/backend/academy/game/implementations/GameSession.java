package backend.academy.game.implementations;

import lombok.Getter;
import lombok.Setter;

/**
 * The model of the game session.
 * This class contains all the information about the current session
 */
@Getter
@Setter
public class GameSession {
    private String answer;
    private String hint;
    @SuppressWarnings({"all"})
    private Integer NUMBER_OF_ATTEMPTS;
    @Getter
    private final static int MINIMAL_NUMBER_OF_ATTEMPTS = 6;
    private boolean gameIsFinished;
    private boolean needHint;
    private int currentAttemptNumber;
    private StringBuilder attemptStringBuilder;
    private String gameResult;

    public GameSession() {
        currentAttemptNumber = 0;
        gameIsFinished = false;
        needHint = false;
    }

}
