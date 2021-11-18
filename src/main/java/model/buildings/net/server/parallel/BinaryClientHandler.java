package model.buildings.net.server.parallel;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.io.*;
import java.net.Socket;

import static model.buildings.net.server.utility.SomeServerUtilities.executeBinaryDataExchange;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BinaryClientHandler implements Runnable {
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;

    public BinaryClientHandler(@NonNull Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
    }

    @Override
    public void run() {
        try {
            executeBinaryDataExchange(in, out);

            if (!in.readLine().equals("stop_it_pls")) {
                System.out.println("WARNING! END MESSAGE WAS NOT RECEIVED");
                clientSocket.close();
            }

            System.out.println("end message received, ok");

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
