package ru.universum.Server;

import ru.universum.Server.sql.DataBase;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import static ru.universum.Server.Service.*;

public class Server extends Thread {
    private static Out out = new Out("Server");
    InetAddress ADDRESS;
    ServerSocket serverSocket;
    List<UserConnection> userConnections = new ArrayList<>();
    Server(){
        DataBase dataBase = new DataBase("jdbc:mysql://localhost:3306/serverdb", "root", "rfccbjgtz");
        dataBase.printAll();
        try {
            ADDRESS = getAddress();
            serverSocket = new ServerSocket(2905, 2, ADDRESS);
            out.printMessage("Created socket on " + ADDRESS.getHostAddress() +":"+serverSocket.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        out.printMessage("Waiting to connect...");
        try{
            while (!interrupted()){
                userConnections.add(new UserConnection(serverSocket.accept(), this));
            }
        } catch (IOException e){
            out.printException(e.toString());
        }
    }
}
