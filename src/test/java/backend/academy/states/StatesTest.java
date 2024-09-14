package backend.academy.states;

import backend.academy.game.implementations.GameSession;
import backend.academy.states.implementations.ConfigureGameState;
import backend.academy.states.implementations.PlayingGameState;
import backend.academy.states.implementations.ResultOfTheGameState;
import backend.academy.words.Word;
import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatesTest {
    GameSession gameSession;

    @BeforeEach
    void setup(){
        gameSession = new GameSession();
    }

    @Test
    @DisplayName("States are changing successfully")
    void testStateChanges(){
        gameSession.answer(new Word("demo", "hint", Category.SPORT, Level.EASY));

        assertThat(gameSession.gameState()).isInstanceOf(ConfigureGameState.class);

        gameSession.gameState().nextState(gameSession);

        assertThat(gameSession.gameState()).isInstanceOf(PlayingGameState.class);

        gameSession.gameState().nextState(gameSession);

        assertThat(gameSession.gameState()).isInstanceOf(ResultOfTheGameState.class);

        gameSession.gameState().nextState(gameSession);

        assertThat(gameSession.gameState()).isInstanceOf(ResultOfTheGameState.class);
    }


}
