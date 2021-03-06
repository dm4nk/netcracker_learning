package model.buildings.net.client;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.buildings.Building;
import model.buildings.net.server.utility.SomeServerUtilities;
import model.utilities.Buildings;
import model.utilities.factories.impl.DwellingFactory;
import model.utilities.factories.impl.HotelFactory;
import model.utilities.factories.impl.OfficeFactory;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static model.buildings.net.server.utility.SomeServerUtilities.END_MSG;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SerialClient {
    static File INFO = new File("src/main/resources/buildingsInfo.txt");
    static File TYPES = new File("src/main/resources/buildingsTypes.txt");
    static File PRICES = new File("src/main/resources/buildingsPrices.txt");

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Reading buildings from file");
        List<Building> buildings = getBuildingList(INFO, TYPES);

        System.out.println("--------------------------------------------");
        for (Building b : buildings)
            System.out.println(b);
        System.out.println("--------------------------------------------");

        try (Socket socket = new Socket("localhost", SomeServerUtilities.SERIAL_PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             PrintWriter outPrices = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PRICES))))) {
            System.out.println("Writing buildings");
            out.writeObject(buildings);

            System.out.println("Reading prices");
            List<String> prices = (List<String>) in.readObject();

            for (String p : prices) {
                System.out.println(p);
                outPrices.write(p + "\n");
            }

            System.out.println("Sending end message");
            out.write(END_MSG);
            out.flush();
        }
    }

    private static List<Building> getBuildingList(File info, File types) {
        List<Building> buildingList = new ArrayList<>();

        try (BufferedReader infoReader = new BufferedReader(new FileReader(info)); BufferedReader typesReader = new BufferedReader(new FileReader(types))) {
            String type;
            while ((type = typesReader.readLine()) != null) {
                switch (type) {
                    case "Hotel":
                        Buildings.setBuildingFactory(new HotelFactory());
                        break;
                    case "Dwelling":
                        Buildings.setBuildingFactory(new DwellingFactory());
                        break;
                    case "Office":
                        Buildings.setBuildingFactory(new OfficeFactory());
                        break;
                }
                buildingList.add(Buildings.readBuilding(infoReader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buildingList;
    }
}
