package ru.universum.Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class InputReader extends Thread {
    static Out out = new Out("InputReader");
    UserConnection userConnection;
    Socket socket;
    DataInputStream dataInputStream;

    InputReader(Socket socket, UserConnection userConnection){
        this.socket = socket;
        this.userConnection = userConnection;
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
            while (!interrupted()){
                userConnection.commandsHandler.handler(userConnection.commandsHandler.rebuildMessage(dataInputStream.readUTF()));
            }
        } catch (IOException e) {
            out.printException("InputReader неожиданно остановился (Socket: "+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+")");
            e.printStackTrace();
            interrupt();
        } finally {
            interrupt();
        }
    }

    public void close() throws IOException {
        dataInputStream.close();
        interrupt();
    }
}
