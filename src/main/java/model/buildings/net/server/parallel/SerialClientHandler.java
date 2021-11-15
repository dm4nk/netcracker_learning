package model.buildings.net.server.parallel;

import lombok.NonNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static model.buildings.net.server.utility.SomeServerUtilities.executeSerialDataExchange;

public class SerialClientHandler implements Runnable {
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public SerialClientHandler(@NonNull Socket clientSocket) throws IOException {
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        try {
            executeSerialDataExchange(in, out);
            in.close();
            out.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
