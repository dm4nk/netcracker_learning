package model.buildings.net.server.parallel;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static model.buildings.net.server.utility.SomeServerUtilities.executeSerialDataExchange;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SerialClientHandler implements Runnable {
    static int END_MSG = 228;

    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;

    public SerialClientHandler(@NonNull Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        try {
            executeSerialDataExchange(in, out);

            if (in.read() != END_MSG) {
                System.out.println("WARNING! END MESSAGE WAS NOT RECEIVED");
                clientSocket.close();
            }

            System.out.println("end message received, ok");

            in.close();
            out.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
