import java.io.IOException;
import java.util.List;

public interface HistoryService {
    void saveHistory (List<String> chat);
    List<String> load() throws IOException;
}
