package model.buildings.net.server.sequential;

import model.buildings.Building;
import model.utilities.Buildings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {
    public static void main(String[] args) throws IOException {

        System.out.println("Waiting for client");
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())))) {
            System.out.println("Clients connected");
            System.out.println("Reading building:\n");

            Building building = Buildings.readBuilding(in);
            System.out.println(building);

            System.out.println("Writing size: " + building.size());
            out.write(building.size());
        }
    }
}
