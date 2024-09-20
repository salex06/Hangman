package backend.academy.util.implementations;

import backend.academy.util.IOHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.Getter;

/**
 * ConsoleInteractor class allows to interact with the console
 * (reading/writing data, flushing the buffer, clearing the console).
 * It is based on the BufferedReader and BufferedWriter classes using
 * System.in and System.out.
 */
@Getter
public class ConsoleInteractor implements IOHandler {
    private final Scanner inputStream;
    private final PrintStream outputStream;

    public ConsoleInteractor() {
        inputStream = new Scanner(System.in, StandardCharsets.UTF_8);
        outputStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    public ConsoleInteractor(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new Scanner(inputStream, StandardCharsets.UTF_8);
        this.outputStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8);
    }

    public void writeMessage(String message) throws IOException {
        outputStream.print(message);
    }

    public String readMessage() throws IOException {
        return inputStream.nextLine();
    }

    public void clearScreen() throws IOException {
        writeMessage("\033[H\033[2J");
    }
}
