package model.buildings.net.server.sequential;

import model.buildings.Building;
import model.exeptions.BuildingUnderArrestException;
import model.utilities.Buildings;
import model.utilities.task6.factories.impl.DwellingFactory;
import model.utilities.task6.factories.impl.HotelFactory;
import model.utilities.task6.factories.impl.OfficeFactory;

import java.io.*;
import java.net.Socket;

import static model.buildings.net.server.sequential.BinaryServer.getPrice;

public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())))) {
            System.out.println("Clients connected");

            System.out.println("Reading size parameter: ");
            int size = in.read();
            System.out.println(size);

            for (int i = 0; i < size; ++i) {
                System.out.println("Reading building type");

                String type = in.readLine();
                switch (type) {
                    case "Dwelling":
                        Buildings.setBuildingFactory(new DwellingFactory());
                        break;
                    case "Office":
                        Buildings.setBuildingFactory(new OfficeFactory());
                        break;
                    case "Hotel":
                        Buildings.setBuildingFactory(new HotelFactory());
                        break;
                }

                System.out.println("Reading building info");
                Building building = Buildings.readBuilding(in);
                System.out.println(building);

                System.out.println("Writing price");
                try {
                    out.write(getPrice(building) + "\n");
                    out.flush();
                } catch (BuildingUnderArrestException e) {
                    out.write("Building is under arrest\n");
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
