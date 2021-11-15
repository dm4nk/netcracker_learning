package model.buildings.net.server.parallel;

import model.buildings.net.server.sequential.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class BinaryServer {
    public static final int PORT = 8080;

    private static final LinkedList<ClientHandler> clientList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Waiting for client");
                Socket socket = serverSocket.accept();

                new ClientHandler(socket);
            }
        }
    }
}
