package model.buildings.net.server.sequential;

import model.buildings.Building;
import model.buildings.net.server.parallel.SerialClientHandler;
import model.buildings.net.server.utility.SomeServerUtilities;
import model.exeptions.BuildingUnderArrestException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static model.buildings.net.server.utility.SomeServerUtilities.executeSerialDataExchange;

public class SerialServer {
    public static final int PORT = 8081;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Waiting for client");
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket clientSocket = serverSocket.accept();
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            System.out.println("Clients connected");

            executeSerialDataExchange(in, out);
        }
    }
}
