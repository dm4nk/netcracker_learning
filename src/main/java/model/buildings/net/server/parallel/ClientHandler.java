package model.buildings.net.server.parallel;

import lombok.NonNull;
import model.buildings.Building;
import model.utilities.SomeServerUtilities;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final BufferedReader in;
    private final PrintWriter out;

    public ClientHandler(@NonNull Socket clientSocket) throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
    }

    @Override
    public void run() {
        try {
            System.out.println("Reading size parameter: ");
            int size = in.read();
            System.out.println(size);

            for (int i = 0; i < size; ++i) {
                Building building = SomeServerUtilities.readBuilding(in);
                System.out.println(building);

                Double price = SomeServerUtilities.writePrice(out, building);
                System.out.println(price);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
