package model.buildings.net.client;

import model.buildings.Building;
import model.utilities.Buildings;
import model.utilities.task6.factories.impl.DwellingFactory;
import model.utilities.task6.factories.impl.OfficeFactory;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BinaryClient {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())))) {
            System.out.println("Connected to server");

            System.out.println("Reading building from file");

            List<Building> buildingList = getBuildingList(new File("src/main/resources/buildingsInfo.txt"), new File("src/main/resources/buildingsTypes.txt"));

            System.out.println("--------------------------------------------");
            for (Building b : buildingList)
                System.out.println(b);
            System.out.println("--------------------------------------------");

            System.out.println("Writing building");
            for (Building b : buildingList)
                Buildings.writeBuilding(b, out);

            System.out.println("Reading sizes");
            List<Integer> sizeList = new ArrayList<>();
            int size;
            while ((size = in.read()) != -1) {
                sizeList.add(size);
            }
            for (int s : sizeList)
                System.out.println(s);
        }
    }

    private static List<Building> getBuildingList(File info, File types) {
        List<Building> buildingList = new ArrayList<>();

        try (BufferedReader infoReader = new BufferedReader(new FileReader(info)); BufferedReader typesReader = new BufferedReader(new FileReader(types))) {
            String type;
            while ((type = typesReader.readLine()) != null) {
                switch (type) {
                    case "Dwelling":
                        Buildings.setBuildingFactory(new DwellingFactory());
                    case "Office":
                        Buildings.setBuildingFactory(new OfficeFactory());
                }
                buildingList.add(Buildings.readBuilding(infoReader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buildingList;
    }
}
