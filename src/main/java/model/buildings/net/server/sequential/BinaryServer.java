package model.buildings.net.server.sequential;

import model.buildings.Building;
import model.utilities.SomeServerUtilities;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        System.out.println("Waiting for client");
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())))) {
            System.out.println("Clients connected");
            System.out.println("Reading size parameter:");
            int size = in.read();
            System.out.println(size);

            for (int i = 0; i < size; ++i) {
                Building building = SomeServerUtilities.readBuilding(in);
                System.out.println(building);

                Double price = SomeServerUtilities.writePrice(out, building);
                System.out.println(price);
            }
        }
    }
}
