import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
    private ServerSocket serverSocket;
    private ArrayList<Socket> clients;

    public GameServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clients = new ArrayList<>();
    }

    public void open() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Socket clientSocket = null;
                    try {
                        clientSocket = serverSocket.accept();
                        clients.add(clientSocket);
                    }
                    catch (IOException e) {}

                }
            }
        }).start();
    }
}
