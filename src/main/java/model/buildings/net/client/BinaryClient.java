package model.buildings.net.client;

import model.buildings.dwelling.Dwelling;
import model.buildings.dwelling.DwellingFloor;
import model.buildings.dwelling.Flat;
import model.utilities.Buildings;

import java.io.*;
import java.net.Socket;

public class BinaryClient {

    public static void main(String[] args) throws IOException {
        DwellingFloor floor1;
        DwellingFloor floor2;
        DwellingFloor floor3;

        Dwelling building1;

        floor1 = new DwellingFloor(new Flat[]{new Flat(1, 1)});
        floor2 = new DwellingFloor(new Flat[]{new Flat(1, 2), new Flat(2, 2)});
        floor3 = new DwellingFloor(new Flat[]{new Flat(1, 3), new Flat(2, 3), new Flat(3, 3)});

        building1 = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});


        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())))) {
            System.out.println("Connected to server");
            System.out.println("Writing building");
            Buildings.writeBuilding(building1, out);
            System.out.println("Reading size");
            int size = in.read();
            System.out.println("Size:" + size);
        }

    }
}
