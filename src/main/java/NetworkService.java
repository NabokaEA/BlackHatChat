import sun.plugin2.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class NetworkService {
    private static NetworkService instance;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    private NetworkService() {
        try {
            Socket socket = new Socket("localhost", 8189);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }catch (Exception e){
            throw new RuntimeException("Не могу создать сетевое соединение");
        }
    }

    public static NetworkService getInstance() throws IOException {
        if (instance==null) {
            instance=new NetworkService();
        }
        return instance;
    }
    public String getUsername(){
        try {
            return ((UserMessage) inputStream.readObject()).getMessageAuthor();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(Arrays.toString(e.getStackTrace()));
        }
    }

    public UserMessage read() throws IOException, ClassNotFoundException {
        return (UserMessage) inputStream.readObject();
    }

    public void write(UserMessage message) throws IOException {
        outputStream.writeObject(message);
        outputStream.flush();
    }
    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }
}
