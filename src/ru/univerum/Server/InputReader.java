package ru.univerum.Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class InputReader extends Thread {
    UserConnection userConnection;
    Socket socket;
    DataInputStream dataInputStream;
    boolean run = false;

    InputReader(Socket socket, UserConnection userConnection){
        this.socket = socket;
        this.userConnection = userConnection;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        run = true;
        start();
    }

    @Override
    public void run() {
        try{
            while (run){
                userConnection.commandsHandler.handler(userConnection.commandsHandler.rebuildMessage(dataInputStream.readUTF()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            interrupt();
            Server.out.printException("InputReader неожиданно остановился (Socket: "+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+")");
        } finally {
            interrupt();
        }
    }

    public void close() throws IOException {
        run = false;
        dataInputStream.close();
        interrupt();
    }
}
