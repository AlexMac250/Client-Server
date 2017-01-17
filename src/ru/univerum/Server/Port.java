package ru.univerum.Server;

/**
 * Created by Aleksandr on 15.01.2017.
 */
public class Port {
    int port;
    int countConnections;
    Connection[] connections = new Connection[15];

    Port(int port){
        this.port = port;
    }
}
