package ru.universum.Client;

import java.util.concurrent.TimeUnit;

public class Client {
    static int port = 2905;
    static String ADDRESS = "10.0.0.9";
    static boolean isConnected = false;
    public static void main(String[] args) {
        Connection.connect();
    }

    public static void start(){
        if (Connection.isConnected){
            Connection.send("userConnection");
        }

        while(isConnected){
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Connected");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    static void connect(int port){
//        System.out.println("Подключение к "+ADDRESS+":"+port+" ...");
//        try {
//            socket = new Socket(ADDRESS, port);
//            inputReader = new InputReader(socket);
//            dataOutputStream = new DataOutputStream(socket.getOutputStream());
//            System.out.println("Подключено к "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
//        } catch (IOException e) {
//            connectionAttempts++;
//            System.err.println("Ошибка! ("+connectionAttempts+") "+ e.getMessage());
//            isConnected = false;
//            if (connectionAttempts < 10) connect(2905);
//        }
//    }

//    static void send(String message){
//        try {
//            dataOutputStream.writeUTF(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
