package backend.academy.states.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.states.interfaces.GameState;
import backend.academy.util.ConsoleInteractor;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayingGameState implements GameState {
    private boolean gameIsFinished = false;
    private int currentAttemptNumber;
    private StringBuilder attemptStringBuilder;
    private boolean gameResult;

    public PlayingGameState(int stringLength) {
        currentAttemptNumber = 0;
        attemptStringBuilder = new StringBuilder().append("-".repeat(Math.max(0, stringLength)));
    }

    @Override
    public void nextState(GameSession gameSession) {
        gameSession.gameState(new ResultOfTheGameState(gameResult));
    }

    @Override
    public void processState(GameSession gameSession) throws IOException {
        ConsoleInteractor consoleInteractor = gameSession.consoleInteractor();

        consoleInteractor.writeMessage("Максимальное количество попыток: " + gameSession.NUMBER_OF_ATTEMPTS() + "\n");
        consoleInteractor.writeMessage("Текущее количество попыток: " + currentAttemptNumber + "\n");

        consoleInteractor.writeMessage(gameSession.gallowsArtist().getCurrGallowsState(
            gameSession.NUMBER_OF_ATTEMPTS(), currentAttemptNumber
        ));

        consoleInteractor.writeMessage("Слово: " + attemptStringBuilder.toString() + "\n");
        consoleInteractor.writeMessage("Введите букву: ");

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

    public boolean manageStateData(String answer, int numberOfAttempts, String currentLetter) {
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

    @Override
    public boolean gameIsFinished() {
        return gameIsFinished;
    }

}
