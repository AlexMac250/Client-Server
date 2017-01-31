package ru.universum.Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static ru.universum.Client.CommandsHandler.rebuildMessage;

public class InputReader extends Thread {
    Socket socket;
    DataInputStream dataInputStream;

    InputReader(Socket socket){
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try{
            dataInputStream = new DataInputStream(socket.getInputStream());
            while (socket.isConnected()){
                CommandsHandler.handler(rebuildMessage(dataInputStream.readUTF()));
            }
        } catch (IOException e) {
            System.err.println("InputReader has crashed (Socket: "+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+")");
            e.printStackTrace();
            interrupt();
        } finally {
            interrupt();
            Client.isConnected = false;
        }
    }
}
