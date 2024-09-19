package backend.academy.util;

/**
 * IOHandler interface provides read and write operations
 */
public interface IOHandler {
    /**
     * Write a string to the output stream
     * @param message message for output
     */
    void writeMessage(String message);

    /**
     * Read a string from the input stream
     * @return message from the input stream
     */
    String readMessage();
}
