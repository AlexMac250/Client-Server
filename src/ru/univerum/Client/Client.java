package ru.univerum.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    static int newport = 2900;
    static String ADDRESS = "10.0.0.10";
    static boolean isConnected = false;
    static Socket socket;
    static DataOutputStream dataOutputStream;
    static InputReader inputReader;
    public static void main(String[] args) {
        connect(2900);
        if (isConnected){
            send("connection");
        }
        while(isConnected){

        }
    }

    static void connect(int port){
        System.out.println("connecting...");
        int i = 0;
        try {
            socket = new Socket(ADDRESS, port);
            inputReader = new InputReader(socket);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("connected to "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
            isConnected = true;
        } catch (IOException e) {
            System.err.println("fail! "+ e.getMessage());
            i++;
            if (i <= 10) connect(2900);
            isConnected = false;
        }
    }

    static void send(String message){
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
