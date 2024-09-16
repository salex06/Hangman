package backend.academy.game.interfaces;

import java.io.IOException;

/**
 * A functional interface that has a single method for starting a session
 */
public interface Session {
    /**
     * Starts the session
     *
     * @throws IOException if an input/output error has occurred
     */
    void start() throws IOException;
}
