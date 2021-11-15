package model.buildings.net.server.parallel;

import model.buildings.Building;
import model.utilities.SomeServerUtilities;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Building building = SomeServerUtilities.readBuilding(clientSocket.getInputStream());
            System.out.println(building);

            Double price = SomeServerUtilities.writePrice(clientSocket.getOutputStream(), building);
            System.out.println(price);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
