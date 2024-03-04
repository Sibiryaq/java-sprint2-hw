import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class Reader {
    List<String> readFile(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
