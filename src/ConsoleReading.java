import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleReading {
    private List<String> file;

    public ConsoleReading(String[] consoleArg) {
        file = new ArrayList<>(List.of(consoleArg));
    }

    public void readingParameters() throws Exception {
        FileHandling sortMerger;

        if (file.contains("-i") || file.contains("-s")) {

            sortMerger = new FileHandling();

            if (file.contains("-i")) {
                FileHandling.type = "-i";
                file.remove("-i");
            } else {
                FileHandling.type = "-s";
                file.remove("-s");
            }

            if (file.contains("-d")) {
                FileHandling.kindSorting = "-d";
                file.remove("-d");
            } else {
                FileHandling.kindSorting = "-a";
                file.remove("-a");
            }

            try {
                if (file.size() <= 1) {
                    throw new Exception("Introduced a small number of files!");
                } else {
                    sortMerger.nameFile(file);
                    sortMerger.playSort();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new Exception("Something went wrong!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new Exception("You did not specify the type of data that is in the file.");
        }
    }
}
