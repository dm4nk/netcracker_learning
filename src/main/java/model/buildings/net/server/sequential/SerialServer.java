package model.buildings.net.server.sequential;

import model.buildings.Building;
import model.buildings.net.server.utility.SomeServerUtilities;
import model.exeptions.BuildingUnderArrestException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SerialServer {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Waiting for client");
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket clientSocket = serverSocket.accept();
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
            System.out.println("Clients connected");

            System.out.println("Reading buildings");
            List<Building> buildings = (List<Building>) in.readObject();

            System.out.println("Writing prices");
            List<String> prices = new ArrayList<>();
            for (Building b : buildings) {
                try {
                    prices.add(SomeServerUtilities.getPrice(b) + "\n");
                } catch (BuildingUnderArrestException e) {
                    prices.add("Building is under arrest");
                }
            }
            out.writeObject(prices);
        }
    }
}
