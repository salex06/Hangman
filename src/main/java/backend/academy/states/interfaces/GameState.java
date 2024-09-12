package backend.academy.states.interfaces;

import backend.academy.game.implementations.GameSession;
import java.io.IOException;

public interface GameState {
    void nextState(GameSession gameSession);

    void processState(GameSession gameSession) throws IOException;

    boolean gameIsFinished();
}
