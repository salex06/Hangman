package backend.academy.strategy.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.strategy.GameStrategy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayGameStrategy implements GameStrategy {
    @Override
    public void processStrategy(String data, GameSession gameSession) {
        String answer = gameSession.answer();

        if (data.equals(String.valueOf("-"))) {
            gameSession.needHint(true);
        } else if (data.length() == 1 && !data.isBlank()) {
            char letter = data.toLowerCase().charAt(0);

            AtomicInteger indexLetter = new AtomicInteger();
            boolean wasUpdated = false;
            List<Map.Entry<Character, Integer>> matchedLetters =
                answer.chars()
                    .mapToObj((currentLetter) ->
                        Map.entry((char) currentLetter, indexLetter.getAndIncrement())
                    ).filter(currentLetter -> (
                        currentLetter.getKey() == letter
                    )).toList();

            if (!matchedLetters.isEmpty()) {
                wasUpdated = true;
                matchedLetters
                    .forEach(currentPair -> gameSession.attemptStringBuilder().setCharAt(currentPair.getValue(),
                        currentPair.getKey()));
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
