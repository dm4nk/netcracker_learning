package model.buildings.net.server.parallel;

import lombok.NonNull;

import java.io.*;
import java.net.Socket;

import static model.buildings.net.server.utility.SomeServerUtilities.executeDataExchange;

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
            executeDataExchange(in, out);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
