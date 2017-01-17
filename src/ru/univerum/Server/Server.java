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
    List<Connection> connections = new ArrayList<>();
    Server(){
        try {
            ADDRESS = getAddress();
            serverSocket = new ServerSocket(2900, 2, ADDRESS);
            setPorts(20000, 20500);
            out.printMessage("created server socket on " + ADDRESS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        out.printMessage("waiting for connection...");
        try{
            while (!interrupted()){
                connections.add(new Connection(serverSocket.accept(), this));
            }
        } catch (IOException e){
            out.printException(e.toString());
        }
    }

    static class out {
        static void printMessage(String string){
            System.out.println("[MESSAGE]: " + string);
        }
        static void printException(String exception){
            System.err.println("[EXCEPTION]: " + exception);
        }
        static void printWarning(String warning){
            System.out.println("[WARNING]: "+ warning);
        }
        static void printError(String error){
            System.err.println("![ERROR]: "+error);
        }
    }
}