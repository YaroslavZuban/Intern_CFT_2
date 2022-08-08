import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ConsoleReading consoleReading = new ConsoleReading(args);

        try {
            consoleReading.readingParameters();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
