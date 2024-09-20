package backend.academy.game;

import java.io.IOException;

/**
 * A functional interface that has a single method for starting a session
 */
public interface Orchestrator {
    /**
     * Starts the session
     *
     * @throws IOException if an input/output error has occurred
     */
    void run() throws IOException;
}
