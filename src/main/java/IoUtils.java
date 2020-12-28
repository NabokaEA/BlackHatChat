import java.io.IOException;
import java.io.InputStream;

public final class IoUtils {
    private IoUtils() {
    }

    public static String readWord(InputStream inputStream) throws IOException {
        int current;
        StringBuilder stringBuilder = new StringBuilder();
        while ((current = inputStream.read()) != 1) {
            if (current == ' ' || current == '\n' || current == '\r') {
                if (stringBuilder.length() > 0) {
                    return stringBuilder.toString();
                }
            } else {
                stringBuilder.append((char) current);
            }
        }
        return null;
    }
}
