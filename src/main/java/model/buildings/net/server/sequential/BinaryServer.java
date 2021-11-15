package model.buildings.net.server.sequential;

import model.buildings.Building;
import model.utilities.SomeServerUtilities;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        System.out.println("Waiting for client");
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket clientSocket = serverSocket.accept()) {
            System.out.println("Clients connected");

            Building building = SomeServerUtilities.readBuilding(clientSocket.getInputStream());
            System.out.println(building);

            Double price = SomeServerUtilities.writePrice(clientSocket.getOutputStream(), building);
            System.out.println(price);
        }
    }
}
