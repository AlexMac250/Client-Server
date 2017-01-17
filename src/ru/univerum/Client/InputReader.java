package ru.univerum.Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static ru.univerum.Client.CommandsHandler.rebuildMessage;

public class InputReader extends Thread {
    Socket socket;
    DataInputStream dataInputStream;
    int errCount = 0;

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
            errCount++;
            e.printStackTrace();
            interrupt();
            if (errCount <= 5) start();
            else System.out.println("InputReader has crashed (Socket: "+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+")");
        } finally {
            interrupt();
        }
    }
}
