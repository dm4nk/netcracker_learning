package model.buildings.net.server.sequential;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.buildings.net.server.utility.SomeServerUtilities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static model.buildings.net.server.utility.SomeServerUtilities.END_MSG;
import static model.buildings.net.server.utility.SomeServerUtilities.executeSerialDataExchange;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SerialServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Waiting for client");
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(SomeServerUtilities.SERIAL_PORT);
                 Socket clientSocket = serverSocket.accept();
                 ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
                System.out.println("Clients connected");

                executeSerialDataExchange(in, out);

                if (in.read() != END_MSG) {
                    System.out.println("WARNING! END MESSAGE WAS NOT RECEIVED");
                    clientSocket.close();
                }

                System.out.println("end message received, ok");
            }
        }
    }
}
