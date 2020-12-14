import sun.plugin2.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkService {
    private static NetworkService instance;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private NetworkService() {
        try {
            socket = new Socket("localhost", 8189);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }catch (Exception e){
            throw new RuntimeException("Не могу создать сетевое соединение")
        }
    }

    public static NetworkService getInstance() throws IOException {
        if (instance==null) {
            instance=new NetworkService();
        }
        return instance;
    }
    public UserMessage read() throws IOException, ClassNotFoundException {
        return (UserMessage) inputStream.readObject();
    }

    public void write(UserMessage message) throws IOException {
        outputStream.writeObject(message);
        outputStream.flush();
    }
}
