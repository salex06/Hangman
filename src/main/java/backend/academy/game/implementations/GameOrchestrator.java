package backend.academy.game.implementations;

import backend.academy.game.Orchestrator;
import backend.academy.strategy.GameStrategy;
import backend.academy.strategy.IStrategyManager;
import backend.academy.strategy.implementations.ConfigGameStrategy;
import backend.academy.strategy.implementations.PlayGameStrategy;
import backend.academy.strategy.implementations.StrategyManager;
import backend.academy.util.IGallowsArtist;
import backend.academy.util.IOHandler;
import backend.academy.util.implementations.ConsoleInteractor;
import backend.academy.util.implementations.GallowsArtist;
import backend.academy.words.IWordsStorage;
import backend.academy.words.Word;
import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import backend.academy.words.implementations.WordsStorage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
public class GameOrchestrator implements Orchestrator {
    private GameStrategy gameStrategy;
    private IOHandler ioHandler;
    private final GameSession gameSession;
    private final IWordsStorage wordsStorage;
    private final IGallowsArtist gallowsArtist;
    private final IStrategyManager strategyManager;

    public GameOrchestrator() {
        gameSession = new GameSession();
        ioHandler = new ConsoleInteractor();
        wordsStorage = new WordsStorage();
        gallowsArtist = new GallowsArtist();
        strategyManager = new StrategyManager();
    }

    private String manageConfig(String dataToSendArg) throws IOException {
        if (Objects.isNull(gameSession.NUMBER_OF_ATTEMPTS())) {
            ioHandler.writeMessage("Select the maximum number of mistakes (at least 6): ");
            return ioHandler.readMessage();
        } else if (Objects.isNull(gameSession.answer())) {
            ioHandler.writeMessage("Available categories");
            ioHandler.writeMessage(
                Arrays.stream(Category.values()).map((i) ->
                    ("\n" + "[" + (i.ordinal() + 1) + "]" + i.name())
                ).toList().stream().reduce((a, b) -> (a + b)).orElseThrow()
            );
            ioHandler.writeMessage("\nEnter the category number: ");
            Category category =
                Category.getCategory(ioHandler.readMessage()).orElseGet(Category::randomCategory);

            ioHandler.writeMessage("Available difficulty levels of the word");
            ioHandler.writeMessage(
                Arrays.stream(Level.values()).map((i) ->
                    ("\n" + "[" + (i.ordinal() + 1) + "]" + i.name())
                ).toList().stream().reduce((a, b) -> (a + b)).orElseThrow()
            );
            ioHandler.writeMessage("\nEnter the level number: ");
            Level level = Level.getLevel(ioHandler.readMessage()).orElseGet(Level::randomLevel);

            Word word = wordsStorage.getRandomWord(category, level);
            return word.value() + " " + word.hint();
        }
        return dataToSendArg;
    }

    private String managePlaying() throws IOException {
        ioHandler.writeMessage("Maximum number of attempts: " + gameSession.NUMBER_OF_ATTEMPTS() + "\n");
        ioHandler.writeMessage("Current number of attempts: " + gameSession.currentAttemptNumber() + "\n");

        ioHandler.writeMessage(gallowsArtist.getCurrGallowsState(
            gameSession.NUMBER_OF_ATTEMPTS(), gameSession.currentAttemptNumber()
        ));

        ioHandler.writeMessage("Word: " + gameSession.attemptStringBuilder().toString() + "\n");

        if (gameSession.needHint()) {
            ioHandler.writeMessage("Hint: " + gameSession.hint() + "\n");
        } else {
            ioHandler.writeMessage("[-]SHOW A HINT\n");
        }

        ioHandler.writeMessage("Enter a letter: ");

        return ioHandler.readMessage().toLowerCase();
    }

    private void manageResult() throws IOException {
        if (gameSession.gameResult().equals(String.valueOf("win"))) {
            ioHandler.writeMessage("Congratulations, you have won!\n");
        } else {
            ioHandler.writeMessage("Unfortunately, you have lost!\n");
        }
        ioHandler.writeMessage("The answer: " + gameSession.answer() + "\n");
    }

    public void run() throws IOException {
        while (!gameSession.gameIsFinished()) {
            try {
                String dataToSend = "";
                gameStrategy = strategyManager.manageStrategy(gameSession);
                if (gameStrategy instanceof ConfigGameStrategy) {
                    dataToSend = manageConfig(dataToSend);
                    ioHandler.clearScreen();
                } else if (gameStrategy instanceof PlayGameStrategy) {
                    dataToSend = managePlaying();
                    ioHandler.clearScreen();
                } else {
                    manageResult();
                }
                gameStrategy.processStrategy(dataToSend, gameSession);
            } catch (IllegalArgumentException e) {
                ioHandler.clearScreen();
                ioHandler.writeMessage("Incorrect input: " + e.getMessage() + "\n");
            } catch (IOException e) {
                Logger.log.error("Error: {}", e.getMessage());
                break;
            }
        }
    }

    @Slf4j
    private static class Logger {
    }
}
