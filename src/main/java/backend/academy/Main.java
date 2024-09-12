package backend.academy;

import backend.academy.game.implementations.GameSession;
import java.io.IOException;
import lombok.experimental.UtilityClass;


@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        GameSession gameSession = new GameSession();
        gameSession.start();
    }
}
