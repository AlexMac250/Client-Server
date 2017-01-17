package ru.univerum.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import static ru.univerum.Server.Service.*;

public class Server extends Thread {
    InetAddress ADDRESS;
    ServerSocket serverSocket;
    List<UserConnection> userConnections = new ArrayList<>();
    Server(){
        try {
            ADDRESS = getAddress();
            serverSocket = new ServerSocket(2905, 2, ADDRESS);
            out.printMessage("Открыт сокет на " + ADDRESS.getHostAddress() +":"+serverSocket.getLocalPort());
            setPorts(40000, 40500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        out.printMessage("Ожидает подключений...");
        try{
            while (!interrupted()){
                userConnections.add(new UserConnection(serverSocket.accept(), this));
            }
        } catch (IOException e){
            out.printException(e.toString());
        }
    }

    static class out {
        static void printMessage(String string){
            System.out.println("[СООБЩЕНИЕ]: " + string);
        }
        static void printException(String exception){
            System.err.println("[ИСКЛЮЧЕНИЕ]: " + exception);
        }
        static void printWarning(String warning){
            System.out.println("[ВНИМАНИЕ]: "+ warning);
        }
        static void printError(String error){
            System.err.println("![ОШИБКА]: "+error);
        }
    }
}
