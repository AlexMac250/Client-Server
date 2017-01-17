package ru.univerum.Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class InputReader extends Thread {
    Connection connection;
    Socket socket;
    DataInputStream dataInputStream;
    int errCount = 0;

    InputReader(Socket socket, Connection connection){
        this.socket = socket;
        this.connection = connection;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        try{
            while (socket.isConnected()){
                connection.commandsHandler.handler(connection.commandsHandler.rebuildMessage(dataInputStream.readUTF()));
            }
        } catch (IOException e) {
            errCount++;
            Server.out.printException("Exceprion on InputReader "+errCount);
            e.printStackTrace();
            interrupt();
            if (errCount <= 5) start();
            else Server.out.printException("InputReader has crashed (Socket: "+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+")");
        } finally {
            Server.out.printMessage("InputReader interrupted");
            interrupt();
        }
    }
}
