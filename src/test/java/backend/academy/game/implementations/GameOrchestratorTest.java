package backend.academy.game.implementations;

import backend.academy.util.IOHandler;
import backend.academy.util.implementations.ConsoleInteractor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameOrchestratorTest {
    GameOrchestrator gameOrchestrator = new GameOrchestrator();
    ByteArrayInputStream byteArrayInputStream;
    ByteArrayOutputStream byteArrayOutputStream;
    IOHandler ioHandler;

    @Test
    @DisplayName("Ensure the game configuration stage completed successfully")
    void ensureGameConfigCompletedSuccessfully() throws IOException {
        byteArrayInputStream = new ByteArrayInputStream("8\n1\n2\n".getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
        ioHandler = new ConsoleInteractor(byteArrayInputStream, byteArrayOutputStream);
        gameOrchestrator.ioHandler(ioHandler);

        try {
            gameOrchestrator.run();
        }catch(IOException | NoSuchElementException _){}

        assertNotNull(gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS());
        assertNotNull(gameOrchestrator.gameSession().answer());
        assertNotNull(gameOrchestrator.gameSession().hint());
    }

    @Test
    @DisplayName("Ensure the game playing stage completed successfully (win)")
    void ensureGamePlayingCompletedSuccessfully_win(){
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(6);
        gameOrchestrator.gameSession().answer("demo");
        gameOrchestrator.gameSession().hint("hint");
        gameOrchestrator.gameSession().attemptStringBuilder(new StringBuilder().append("-".repeat(4)));
        byteArrayInputStream = new ByteArrayInputStream("abc\nd\n-\ne\nM\nk\n\no".getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
        ioHandler = new ConsoleInteractor(byteArrayInputStream, byteArrayOutputStream);
        gameOrchestrator.ioHandler(ioHandler);

        try {
            gameOrchestrator.run();
        }catch(IOException | NoSuchElementException _){}

        assertThat(gameOrchestrator.gameSession().gameResult()).isEqualTo("win");
        assertThat(gameOrchestrator.gameSession().currentAttemptNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("Ensure the game playing stage completed successfully (lose)")
    void ensureGamePlayingCompletedSuccessfully_lose(){
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(6);
        gameOrchestrator.gameSession().answer("demo");
        gameOrchestrator.gameSession().hint("hint");
        gameOrchestrator.gameSession().attemptStringBuilder(new StringBuilder().append("-".repeat(4)));
        byteArrayInputStream = new ByteArrayInputStream("abc\nA\n-\nK\nl\nz\n\no\nR\nX".getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
        ioHandler = new ConsoleInteractor(byteArrayInputStream, byteArrayOutputStream);
        gameOrchestrator.ioHandler(ioHandler);

        try {
            gameOrchestrator.run();
        }catch(IOException | NoSuchElementException _){}

        assertThat(gameOrchestrator.gameSession().gameResult()).isEqualTo("lose");
        assertThat(gameOrchestrator.gameSession().currentAttemptNumber()).isEqualTo(6);
    }

    @Test
    @DisplayName("Ensure the game result stage completed successfully (win)")
    void ensureGameResultStageCompletedSuccessfully_win(){
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(6);
        gameOrchestrator.gameSession().answer("demo");
        gameOrchestrator.gameSession().hint("hint");
        gameOrchestrator.gameSession().gameResult("win");

        try {
            gameOrchestrator.run();
        }catch(IOException | NoSuchElementException _){}

        assertThat(gameOrchestrator.gameSession().gameIsFinished()).isTrue();
    }

    @Test
    @DisplayName("Ensure the game result stage completed successfully (lose)")
    void ensureGameResultStageCompletedSuccessfully_lose(){
        gameOrchestrator.gameSession().NUMBER_OF_ATTEMPTS(6);
        gameOrchestrator.gameSession().answer("demo");
        gameOrchestrator.gameSession().hint("hint");
        gameOrchestrator.gameSession().gameResult("lose");

        try {
            gameOrchestrator.run();
        }catch(IOException | NoSuchElementException _){}

        assertThat(gameOrchestrator.gameSession().gameIsFinished()).isTrue();
    }
}
