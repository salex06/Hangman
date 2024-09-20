package backend.academy.util.implementations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleInteractorTest {
    ConsoleInteractor consoleInteractor;

    @Test
    @DisplayName("Ensure the fields are initialized successfully")
    void ensureConstructorsWorksSuccessfully() {
        consoleInteractor = new ConsoleInteractor();
        assertThat(consoleInteractor.inputStream()).isInstanceOf(Scanner.class);
        assertThat(consoleInteractor.outputStream()).isInstanceOf(PrintStream.class);

        consoleInteractor = new ConsoleInteractor(System.in, System.out);
        assertThat(consoleInteractor.inputStream()).isInstanceOf(Scanner.class);
        assertThat(consoleInteractor.outputStream()).isInstanceOf(PrintStream.class);
    }

    @Test
    @DisplayName("ensure the readMessage method works correctly")
    void ensureReadMessageWorksCorrectly() throws IOException {
        consoleInteractor = Mockito.mock(ConsoleInteractor.class);
        Mockito.when(consoleInteractor.readMessage()).thenReturn("data from input");
        assertEquals("data from input", consoleInteractor.readMessage());

        consoleInteractor = new ConsoleInteractor(new ByteArrayInputStream("buffer".getBytes()), System.out);
        assertEquals("buffer", consoleInteractor.readMessage());
    }

    @Test
    @DisplayName("ensure the writeMessage method works correctly")
    void ensureWriteMessageWorksCorrectly() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        consoleInteractor = new ConsoleInteractor(System.in, byteArrayOutputStream);

        consoleInteractor.writeMessage("test message");

        assertEquals("test message", byteArrayOutputStream.toString());
    }
}
