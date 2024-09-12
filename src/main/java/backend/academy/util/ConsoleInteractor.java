package backend.academy.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import lombok.Getter;

@Getter
public class ConsoleInteractor {
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;

    public ConsoleInteractor() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
    }

    public void writeMessage(String message) throws IOException {
        bufferedWriter.write(message);
    }

    public String readMessage() throws IOException {
        return bufferedReader.readLine();
    }

    public void flushBuffer() throws IOException {
        bufferedWriter.flush();
    }

    public void clearConsole() throws IOException {
        writeMessage("\033[H\033[2J");
        flushBuffer();
    }
}
