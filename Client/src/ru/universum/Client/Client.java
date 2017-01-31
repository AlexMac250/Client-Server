package ru.universum.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
    static int connectionAttempts = 0;
    static int newport = 2905;
    static String ADDRESS = "10.0.0.10";
    static boolean isConnected = false;
    static Socket socket;
    static DataOutputStream dataOutputStream;
    static InputReader inputReader;
    public static void main(String[] args) {
        connect(2905);
        if (isConnected){
            send("connection");
        }
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(isConnected){

        }
    }

    static void connect(int port){
        System.out.println("Подключение к "+ADDRESS+":"+port+" ...");
        try {
            socket = new Socket(ADDRESS, port);
            inputReader = new InputReader(socket);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Подключено к "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
        } catch (IOException e) {
            connectionAttempts++;
            System.err.println("Ошибка! ("+connectionAttempts+") "+ e.getMessage());
            isConnected = false;
            if (connectionAttempts < 10) connect(2905);
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
