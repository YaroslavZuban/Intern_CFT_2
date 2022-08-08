import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.*;
import java.util.stream.Collectors;


public class FileHandling {
    private SortType sort;
    public static String type;
    public static String kindSorting = "-a";
    private static List<String> mass = new ArrayList<>();
    public static List<String> nameFile = new ArrayList<>();
    private List<String> temp = new ArrayList<>();

    public void playSort() throws Exception {
        files();
    }

    public void nameFile(List<String> name) {
        this.nameFile = name;
    }

    private void files() throws Exception {

        if (typeDefinitions() == 0) {
            throw new Exception("Incorrect data");
        } else {
            sort = new SortType();

            for (int i = 1; i < nameFile.size(); i++) {

                try (Scanner scanner = new Scanner(new File( nameFile.get(i)))) {
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        temp.add(line);

                        if ("".equals(temp.get(temp.size() - 1)) || temp.contains(" ") || temp.get(temp.size() - 1).contains(" ")) {
                            throw new Exception("There is empty space in " + nameFile.get(i));
                        }

                        if (type.equals("-i") && searchStringChar(line)) {
                            throw new Exception("There is a character in numbers.");
                        }
                    }

                    sort.sortWatch(temp, i);

                    mass.addAll(temp);

                    sort.setList(mass);
                    sort.sortPlay();

                    temp.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            reader();
        }
    }

    public boolean searchStringChar(String line) throws Exception {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) < '0' || line.charAt(i) > '9') {
                return true;
            }
        }

        return false;
    }

    private int typeDefinitions() {
        if ("-i".equals(type)) {
            return 1;
        } else if ("-s".equals(type)) {
            return 2;
        }

        return 0;
    }
    public static void reader() throws IOException {
        Files.newBufferedWriter(Path.of(nameFile.get(0)), new StandardOpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});
        List<String> newList = (List<String>) mass.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        Files.write(Paths.get(nameFile.get(0)), newList, StandardOpenOption.CREATE);
    }

}

