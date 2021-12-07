package model.buildings.net.server.utility;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import model.buildings.Building;
import model.buildings.dwelling.Dwelling;
import model.buildings.dwelling.hotel.Hotel;
import model.buildings.office.OfficeBuilding;
import model.exeptions.BuildingUnderArrestException;
import model.utilities.Buildings;
import model.utilities.factories.impl.DwellingFactory;
import model.utilities.factories.impl.HotelFactory;
import model.utilities.factories.impl.OfficeFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class SomeServerUtilities {
    private static final double PROBABILITY_OF_ARREST = 0.1;
    public static int BINARY_PORT = 8080;
    public static int SERIAL_PORT = 8081;
    public static int END_MSG = 228;
    static int PRICE_FOR_DWELLING = 1000;
    static int PRICE_FOR_OFFICE = 1500;
    static int PRICE_FOR_HOTEL = 2000;

    public static void executeSerialDataExchange(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
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
    }

    public static void executeBinaryDataExchange(BufferedReader in, PrintWriter out) throws IOException {
        System.out.println("Reading size parameter: ");
        int size = in.read();
        System.out.println(size);

        for (int i = 0; i < size; ++i) {
            Building building = SomeServerUtilities.readBuilding(in);
            System.out.println(building);

            Double price = SomeServerUtilities.writePrice(out, building);
            System.out.println(price);
        }
    }


    public static Building readBuilding(@NonNull BufferedReader in) throws IOException {
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
        return Buildings.readBuilding(in);
    }

    public static Double writePrice(@NonNull PrintWriter out, @NonNull Building building) {
        Double price = null;
        System.out.println("Writing price");
        try {
            price = getPrice(building);
            out.write(price + "\n");
            out.flush();
        } catch (BuildingUnderArrestException e) {
            out.write("Building is under arrest\n");
            out.flush();
        }

        return price;
    }

    public static Double getPrice(@NonNull Building building) throws BuildingUnderArrestException {
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
