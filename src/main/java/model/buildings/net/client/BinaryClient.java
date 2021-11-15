package model.buildings.net.client;

import model.buildings.Building;
import model.utilities.Buildings;
import model.utilities.factories.impl.DwellingFactory;
import model.utilities.factories.impl.OfficeFactory;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BinaryClient {
    public static final int PORT = 8080;
    private static final File INFO = new File("src/main/resources/buildingsInfo.txt");
    private static final File TYPES = new File("src/main/resources/buildingsTypes.txt");
    private static final File PRICES = new File("src/main/resources/buildingsPrices.txt");

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
             PrintWriter outPrices = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PRICES))))) {
            System.out.println("Connected to server");

            System.out.println("Reading building info and types from files");

            List<String> buildingTypeList = getBuildingTypeList(TYPES);
            List<String> buildingInfoList = getBuildingInfoList(INFO);

            System.out.println("--------------------------------------------");
            for (String t : buildingTypeList)
                System.out.println(t);

            for (String i : buildingInfoList)
                System.out.println(i);
            System.out.println("--------------------------------------------");

            System.out.println("Passing size parameter");
            out.write(buildingTypeList.size());
            out.flush();

            for (int i = 0; i < buildingTypeList.size(); ++i) {
                System.out.println("Writing building type");
                out.write(buildingTypeList.get(i) + "\n");
                out.flush();

                System.out.println("Writing building info");
                out.write(buildingInfoList.get(i) + "\n");
                out.flush();

                System.out.println("Reading price");
                String price = in.readLine();
                System.out.println(price);
                outPrices.write(price + "\n");
            }
        }
    }

    private static List<String> getBuildingTypeList(File types) {
        List<String> buildingList = new ArrayList<>();
        try (BufferedReader typesReader = new BufferedReader(new FileReader(types))) {
            String type;
            while ((type = typesReader.readLine()) != null) {
                buildingList.add(type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buildingList;
    }

    private static List<String> getBuildingInfoList(File info) {
        List<String> buildingList = new ArrayList<>();
        try (BufferedReader typesReader = new BufferedReader(new FileReader(info))) {
            String type;
            while ((type = typesReader.readLine()) != null) {
                buildingList.add(type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buildingList;
    }

    private static List<Building> getBuildingList(File info, File types) {
        List<Building> buildingList = new ArrayList<>();

        try (BufferedReader infoReader = new BufferedReader(new FileReader(info)); BufferedReader typesReader = new BufferedReader(new FileReader(types))) {
            String type;
            while ((type = typesReader.readLine()) != null) {
                switch (type) {
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
