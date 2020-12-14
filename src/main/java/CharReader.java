import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.scene.control.TextArea;

public class CharReader extends Thread {

    private final TextArea outStream;
    private final ObjectInputStream inputStream;

    public CharReader(TextArea outStream, ObjectInputStream inputStream) {
        this.outStream = outStream;
        this.inputStream = inputStream;
    }


    @Override
    public void run() {
       // setDaemon(true);
        while (true){
            try {
                UserMessage userMessage= (UserMessage) inputStream.readObject();
                outStream.appendText(userMessage.toString());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
