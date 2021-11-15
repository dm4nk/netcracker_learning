package model.buildings.net.server.parallel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Waiting for client");
                Socket socket = serverSocket.accept();
                System.out.println("Clients connected");

                Thread thread = new Thread(new BinaryClientHandler(socket));
                thread.start();
            }
        }
    }
}
