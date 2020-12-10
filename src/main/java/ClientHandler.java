import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable, Closeable {

    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private static int connectionsCount = 0;
    private int connectionID = 0;
    private final String userName;
    private boolean isRunning;
    private final byte[] buffer;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


    public ClientHandler(Socket socket) throws IOException {
        connectionsCount++;
        connectionID = connectionsCount;
        userName = "user#" + connectionID;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        isRunning = true;
        buffer = new byte[256];

    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                int bytesRead = inputStream.read(buffer);
                String messageFromClient = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                if (messageFromClient.equals("/end")) {
                    System.out.println("Received \\End from client" + connectionID);
                    break;
                }
                System.out.println("Received from client:" + userName + " " + messageFromClient);

            } catch (IOException e) {
                System.err.println("Exception while read");
                break;
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        outputStream.write((userName + ": " + message).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
}
