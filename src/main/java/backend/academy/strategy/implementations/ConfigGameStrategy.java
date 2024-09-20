package backend.academy.strategy.implementations;

import backend.academy.game.implementations.GameSession;
import backend.academy.strategy.GameStrategy;
import java.util.Objects;

public class ConfigGameStrategy implements GameStrategy {
    @Override
    public void processStrategy(String data, GameSession gameSession) {
        if (Objects.isNull(gameSession.NUMBER_OF_ATTEMPTS())) {
            int numberOfAttempts = Integer.parseInt(data);
            if (numberOfAttempts < GameSession.MINIMAL_NUMBER_OF_ATTEMPTS()) {
                throw new IllegalArgumentException(
                    "The number of possible mistakes is at least "
                        + GameSession.MINIMAL_NUMBER_OF_ATTEMPTS()
                );
            }
            gameSession.NUMBER_OF_ATTEMPTS(numberOfAttempts);
        } else if (Objects.isNull(gameSession.answer())) {
            String[] splitted = data.split(" ");
            String answer = splitted[0];
            String hint = splitted[1];
            if (splitted.length != 2 || answer.isEmpty() || hint.isEmpty()) {
                throw new IllegalArgumentException("incorrect string with the answer and hint");
            }

            gameSession.answer(answer);
            gameSession.hint(hint);
            gameSession.attemptStringBuilder(new StringBuilder().append("-".repeat(answer.length())));
        }
    }

}
