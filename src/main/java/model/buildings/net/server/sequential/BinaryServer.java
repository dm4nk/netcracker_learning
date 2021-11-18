package model.buildings.net.server.sequential;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.buildings.net.server.utility.SomeServerUtilities;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static model.buildings.net.server.utility.SomeServerUtilities.executeBinaryDataExchange;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BinaryServer {

    public static void main(String[] args) throws IOException {
        System.out.println("Waiting for client");
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(SomeServerUtilities.BINARY_PORT);

                 Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())))) {

                System.out.println("Clients connected");
                System.out.println("Reading size parameter:");
                executeBinaryDataExchange(in, out);

                if (!in.readLine().equals("stop_it_pls")) {
                    System.out.println("WARNING! END MESSAGE WAS NOT RECEIVED");
                    clientSocket.close();
                }

                System.out.println("end message received, ok");
            }

        }
    }
}
