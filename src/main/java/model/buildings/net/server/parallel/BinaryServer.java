package model.buildings.net.server.parallel;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.buildings.net.server.utility.SomeServerUtilities;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BinaryServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(SomeServerUtilities.BINARY_PORT)) {
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
