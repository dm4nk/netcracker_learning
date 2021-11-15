package model.buildings.net.server.parallel;

import lombok.NonNull;

import java.io.*;
import java.net.Socket;

import static model.buildings.net.server.utility.SomeServerUtilities.executeBinaryDataExchange;

public class BinaryClientHandler implements Runnable {
    private final BufferedReader in;
    private final PrintWriter out;

    public BinaryClientHandler(@NonNull Socket clientSocket) throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
    }

    @Override
    public void run() {
        try {
            executeBinaryDataExchange(in, out);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
