package ru.universum.Server;

/**
 * Created by Aleksandr on 15.01.2017.
 */
public class Port {
    int port;
    int countConnections;
    UserConnection[] userConnections = new UserConnection[15];

    Port(int port){
        this.port = port;
        countConnections = 0;
    }


}
