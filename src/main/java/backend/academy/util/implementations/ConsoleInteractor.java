package backend.academy.util.implementations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import lombok.Getter;

/**
 * ConsoleInteractor class allows to interact with the console
 * (reading/writing data, flushing the buffer, clearing the console).
 * It is based on the BufferedReader and BufferedWriter classes using
 * System.in and System.out.
 */
@Getter
public class ConsoleInteractor {
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;

    public ConsoleInteractor() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
    }

    public ConsoleInteractor(InputStream inputStream, OutputStream outputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
    }

    /**
     * Writes data to a BufferedWriter
     *
     * @param message data for writing
     * @throws IOException if an output error has occurred
     */
    public void writeMessage(String message) throws IOException {
        bufferedWriter.write(message);
    }

    /**
     * Reads data from a bufferedReader
     *
     * @return user input
     * @throws IOException if an input error has occurred
     */
    public String readMessage() throws IOException {
        return bufferedReader.readLine();
    }

    /**
     * Flushes the bufferedWriter for displaying messages to the console
     *
     * @throws IOException if a buffer flushing error has occurred
     */
    public void flushBuffer() throws IOException {
        bufferedWriter.flush();
    }

    /**
     * Clears the console of previous messages.
     * It only works in consoles with ASCII code support
     *
     * @throws IOException if an output error has occurred
     */
    public void clearConsole() throws IOException {
        writeMessage("\033[H\033[2J");
        flushBuffer();
    }
}
