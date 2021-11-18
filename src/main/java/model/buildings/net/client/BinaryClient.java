package model.buildings.net.client;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.buildings.net.server.utility.SomeServerUtilities;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BinaryClient {
    static File INFO = new File("src/main/resources/buildingsInfo.txt");
    static File TYPES = new File("src/main/resources/buildingsTypes.txt");
    static File PRICES = new File("src/main/resources/buildingsPrices.txt");

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", SomeServerUtilities.BINARY_PORT);
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

            System.out.println("Sending end message");
            out.write("stop_it_pls");
            out.flush();
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
}
