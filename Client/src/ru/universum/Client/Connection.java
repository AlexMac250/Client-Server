package ru.universum.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Aleksandr on 07.02.2017.
 */
public class Connection {
    Socket socket;
    DataOutputStream outputStream;
    InputReader inputReader;
    static Connection connection;
    static boolean isConnected;

    static void connect() {
        if (connection != null) {
            if (connection.socket.isClosed()) connection = new Connection(Client.port);
        } else connection = new Connection(Client.port);
    }
    static void reconnect(int port){
        isConnected = false;
        try {
            connection.socket.close();
            connection.outputStream.close();
            connection.inputReader.CLOSE();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection = new Connection(port);
    }

    private Connection(int port){
        try {
            System.out.println("Set connection "+port);
            socket = new Socket(Client.ADDRESS, port);
            inputReader = new InputReader(socket);
            outputStream = new DataOutputStream(socket.getOutputStream());
            if (port != Client.port) Client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isConnected = true;
    }
    static void send(String message){
        try {
            connection.outputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
