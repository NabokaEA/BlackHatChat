import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class FileHistoryService implements HistoryService{
    private static FileHistoryService instance;
    private String fileHistoryPath="history.txt";

    private FileHistoryService() {

    }

    public static FileHistoryService getInstance() {

        return instance==null? new FileHistoryService():instance;
    }

    @Override
    public void saveHistory(List<String> chat) {
        try {
            Files.write(Paths.get(fileHistoryPath), chat, StandardOpenOption.CREATE);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> load() throws IOException {
        try {
            return Files.newBufferedReader(Paths.get(fileHistoryPath)).lines().collect(Collectors.toList());
        }
        catch (IOException e){
            throw new RuntimeException("File Not Found");
        }

    }
}
