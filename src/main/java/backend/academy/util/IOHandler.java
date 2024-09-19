package backend.academy.util;

public interface IOHandler {
    void writeMessage(String message);

    String readMessage();
}
