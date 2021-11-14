package model.buildings.net.server.sequential;

import model.buildings.Building;
import model.buildings.dwelling.Dwelling;
import model.buildings.dwelling.hotel.Hotel;
import model.buildings.office.OfficeBuilding;
import model.exeptions.BuildingUnderArrestException;
import model.utilities.Buildings;
import model.utilities.task6.factories.impl.DwellingFactory;
import model.utilities.task6.factories.impl.HotelFactory;
import model.utilities.task6.factories.impl.OfficeFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {
    private static final int PRICE_FOR_DWELLING = 1000;
    private static final int PRICE_FOR_OFFICE = 1500;
    private static final int PRICE_FOR_HOTEL = 2000;

    private static final double PROBABILITY_OF_ARREST = 0.1;


    public static void main(String[] args) throws IOException {
        System.out.println("Waiting for client");
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())))) {
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
        }
    }

    private static Double getPrice(Building building) throws BuildingUnderArrestException {
        double sum = building.sumSquare();

        if (buildingUnderArrest()) throw new BuildingUnderArrestException();

        if (building instanceof Hotel) return sum * PRICE_FOR_HOTEL;
        if (building instanceof Dwelling) return sum * PRICE_FOR_DWELLING;
        if (building instanceof OfficeBuilding) return sum * PRICE_FOR_OFFICE;

        else return null;
    }

    private static boolean buildingUnderArrest() {
        return Math.random() < PROBABILITY_OF_ARREST;
    }
}
