package backend.academy;

import backend.academy.game.implementations.GameOrchestrator;
import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        GameOrchestrator gameOrchestrator = new GameOrchestrator();
        gameOrchestrator.run();
    }
}
