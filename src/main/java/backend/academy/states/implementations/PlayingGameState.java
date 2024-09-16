package backend.academy.states.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.states.interfaces.GameState;
import backend.academy.util.ConsoleInteractor;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

/**
 * The PlayingGameState class implements GameState interface and
 * represents the state of the main part of the game.
 */
@Getter
@Setter
public class PlayingGameState implements GameState {
    private boolean gameIsFinished = false;
    private boolean needHint = false;
    private int currentAttemptNumber;
    private StringBuilder attemptStringBuilder;
    private boolean gameResult;

    /**
     * Defines the currentAttemptNumber and attemptStringBuilder fields
     *
     * @param stringLength the length of the answer
     */
    public PlayingGameState(int stringLength) {
        currentAttemptNumber = 0;
        attemptStringBuilder = new StringBuilder().append("-".repeat(Math.max(0, stringLength)));
    }

    /**
     * Sets the game state as ResultOfTheGameState
     *
     * @param gameSession the current session of the game
     */
    @Override
    public void nextState(GameSession gameSession) {
        gameSession.gameState(new ResultOfTheGameState(gameResult));
    }

    /**
     * Handles the main game process
     *
     * @param gameSession the current session of the game
     * @throws IOException if an input/output error has occurred
     */
    @Override
    public void processState(GameSession gameSession) throws IOException {
        ConsoleInteractor consoleInteractor = gameSession.consoleInteractor();

        consoleInteractor.writeMessage("Maximum number of attempts: " + gameSession.NUMBER_OF_ATTEMPTS() + "\n");
        consoleInteractor.writeMessage("Current number of attempts: " + currentAttemptNumber + "\n");

        consoleInteractor.writeMessage(gameSession.gallowsArtist().getCurrGallowsState(
            gameSession.NUMBER_OF_ATTEMPTS(), currentAttemptNumber
        ));

        consoleInteractor.writeMessage("Word: " + attemptStringBuilder.toString() + "\n");

        if (needHint) {
            consoleInteractor.writeMessage("Hint: " + gameSession.answer().hint() + "\n");
        } else {
            consoleInteractor.writeMessage("[-]SHOW A HINT\n");
        }

        consoleInteractor.writeMessage("Enter a letter: ");

        consoleInteractor.flushBuffer();

        String currentLetter = consoleInteractor.readMessage().toLowerCase();

        if (currentLetter.length() == 1) {
            boolean manageStateDataResult =
                manageStateData(gameSession.answer().value(), gameSession.NUMBER_OF_ATTEMPTS(), currentLetter);
            if (manageStateDataResult) {
                nextState(gameSession);
            }
        }

        consoleInteractor.clearConsole();
    }

    /**
     * Controls the current state of the game according to the current attempt
     *
     * @param answer           the hidden word
     * @param numberOfAttempts the current number of attempts
     * @param currentLetter    the entered character
     * @return true if the game is over; false if the game hasn't been over yet
     */
    public boolean manageStateData(String answer, int numberOfAttempts, String currentLetter) {
        if (currentLetter.equals(String.valueOf('-'))) {
            needHint = true;
            return false;
        }
        if (!updateAttemptStringBuilder(answer, currentLetter.charAt(0))) {
            currentAttemptNumber++;
        }
        boolean checkResult = compareAnswerToCurrent(answer);
        if (!checkResult && currentAttemptNumber == numberOfAttempts) {
            gameResult = false;
            return true;
        } else if (checkResult) {
            gameResult = true;
            return true;
        }
        return false;
    }

    /**
     * Manages the StringBuilder - the state of the hidden word - according to the current attempt
     *
     * @param answer        the hidden word
     * @param currentLetter the entered character
     * @return true if the currentLetter is found in the answer, else - false
     */
    public boolean updateAttemptStringBuilder(String answer, char currentLetter) {
        boolean wasUpdated = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == currentLetter) {
                wasUpdated = true;
                attemptStringBuilder.setCharAt(i, currentLetter);
            }
        }
        return wasUpdated;
    }

    /**
     * Compares the answer with the current word
     *
     * @param answer the hidden word
     * @return true if answer is equal to the hidden word, else - false
     */
    public boolean compareAnswerToCurrent(String answer) {
        String compareToAnswer = attemptStringBuilder.toString();
        boolean successfulCompare = true;
        for (int i = 0; i < answer.length(); i++) {
            if (compareToAnswer.charAt(i) != answer.charAt(i)) {
                successfulCompare = false;
                break;
            }
        }
        return successfulCompare;
    }

    /**
     * Check the game is finished (it's always false, because the game has not finished yet)
     *
     * @return boolean type variable "gameIsFinished"
     */
    @Override
    public boolean gameIsFinished() {
        return gameIsFinished;
    }

}
