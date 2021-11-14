package model.buildings.net.client;

import java.io.IOException;
import java.net.Socket;

public class BinaryClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
    }
}
