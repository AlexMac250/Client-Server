package ru.univerum.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection extends Thread{
    Port port;
    Socket socket;
    DataOutputStream dataOutputStream;
    InputReader inputReader;
    CommandsHandler commandsHandler;
    Server server;


    Connection(Socket socket, Server server){
        this.server = server;
        this.socket = socket;
        inputReader = new InputReader(socket, this);
        setDOStream();
        setNewPort();
        start();
    }

    private void setDOStream(){
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    private void setNewPort(){
        Port newport = Service.getOpenPort(this);
        try {
            dataOutputStream.writeUTF("newport "+newport.port);
            port = newport;
            socket = new ServerSocket(newport.port, 2, server.ADDRESS).accept();
            setDOStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
