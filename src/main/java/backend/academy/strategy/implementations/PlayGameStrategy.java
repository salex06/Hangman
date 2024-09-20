package backend.academy.strategy.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.strategy.GameStrategy;

public class PlayGameStrategy implements GameStrategy {
    @Override
    public void processStrategy(String data, GameSession gameSession) {
        String answer = gameSession.answer();

        if (data.equals(String.valueOf("-"))) {
            gameSession.needHint(true);
        } else if (data.length() == 1 && !data.isBlank()) {
            char letter = data.toLowerCase().charAt(0);
            boolean wasUpdated = false;
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == letter) {
                    wasUpdated = true;
                    gameSession.attemptStringBuilder().setCharAt(i, letter);
                }
            }

            if (!wasUpdated) {
                gameSession.currentAttemptNumber(gameSession.currentAttemptNumber() + 1);
                if (gameSession.currentAttemptNumber() == gameSession.NUMBER_OF_ATTEMPTS()) {
                    gameSession.gameResult("lose");
                    return;
                }
            }

            String currentStringBuilderState = gameSession.attemptStringBuilder().toString();
            if (currentStringBuilderState.equals(answer)) {
                gameSession.gameResult("win");
            }
        }
    }
}
