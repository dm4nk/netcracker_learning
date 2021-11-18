package model.buildings.net.server.sequential;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static model.buildings.net.server.utility.SomeServerUtilities.executeBinaryDataExchange;

public class BinaryServer {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Waiting for client");
            try (ServerSocket serverSocket = new ServerSocket(PORT);
                 Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())))) {
                System.out.println("Clients connected");
                System.out.println("Reading size parameter:");
                executeBinaryDataExchange(in, out);
            }
        }
    }
}
