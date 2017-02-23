package ru.universum.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Port {
    int port;
    int countConnections;
    UserConnection[] userConnections = new UserConnection[15];
    ServerSocket socket;

    Port(int port){
        this.port = port;
        countConnections = 0;
        try {
            socket = new ServerSocket(this.port, 2,Main.server.ADDRESS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getSocket() {
        return socket;
    }

    public void closeConnection(){
        countConnections--;
    }
}
