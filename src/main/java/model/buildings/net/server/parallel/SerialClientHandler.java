package model.buildings.net.server.parallel;

import lombok.NonNull;
import model.buildings.Building;
import model.buildings.net.server.utility.SomeServerUtilities;
import model.exeptions.BuildingUnderArrestException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SerialClientHandler implements Runnable {
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public SerialClientHandler(@NonNull Socket clientSocket) throws IOException {
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        try {
            System.out.println("Reading buildings");
            List<Building> buildings = (List<Building>) in.readObject();

            System.out.println("Writing prices");
            List<String> prices = new ArrayList<>();
            for (Building b : buildings) {
                try {
                    prices.add(String.valueOf(SomeServerUtilities.getPrice(b)));
                } catch (BuildingUnderArrestException e) {
                    prices.add("Building is under arrest");
                }
            }
            out.writeObject(prices);

            in.close();
            out.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
