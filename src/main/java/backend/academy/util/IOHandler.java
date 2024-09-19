package backend.academy.util;

import java.io.IOException;

/**
 * IOHandler interface provides read and write operations
 */
public interface IOHandler {
    /**
     * Write a string to the output stream
     *
     * @param message message for output
     * @throws IOException if an output error has occurred
     */
    void writeMessage(String message) throws IOException;

    /**
     * Read a string from the input stream
     *
     * @return message from the input stream
     * @throws IOException if an input error has occurred
     */
    String readMessage() throws IOException;
}
