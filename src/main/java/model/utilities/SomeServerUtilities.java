package model.utilities;

import lombok.NonNull;
import model.buildings.Building;
import model.buildings.dwelling.Dwelling;
import model.buildings.dwelling.hotel.Hotel;
import model.buildings.office.OfficeBuilding;
import model.exeptions.BuildingUnderArrestException;
import model.utilities.factories.impl.DwellingFactory;
import model.utilities.factories.impl.HotelFactory;
import model.utilities.factories.impl.OfficeFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class SomeServerUtilities {
    private static final int PRICE_FOR_DWELLING = 1000;
    private static final int PRICE_FOR_OFFICE = 1500;
    private static final int PRICE_FOR_HOTEL = 2000;

    private static final double PROBABILITY_OF_ARREST = 0.1;


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
