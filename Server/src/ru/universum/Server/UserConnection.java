package ru.universum.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UserConnection extends Thread{
    private Out out = new Out("UserConnection");

    Port port;
    Socket socket;
    DataOutputStream dataOutputStream;
    InputReader inputReader;
    CommandsHandler commandsHandler;
    Server server;


    UserConnection(Socket socket, Server server){
        out.printMessage("Новое подключение");
        try {
            this.server = server;
            this.socket = socket;
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            setNewPort();
            setDOStream();
            start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
                socket.close();
                port.closeConnection();
            } catch (IOException ignored) {
                out.printException("Иключение при закрытии соединения");
            }
        }
    }

    private void setDOStream(){
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            interrupt();
        }
    }

    @Override
    public void run() {
        send("connection true");
    }

    private void setNewPort(){
        Port newport = Service.getOpenPort(this);
        try {
            socket = newport.socket.accept();
            dataOutputStream.writeUTF("newport "+newport.port);
            dataOutputStream.close();
            socket.close();
            port = newport;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void send(String message){
        if (socket.isConnected()){
            if (dataOutputStream == null){
                try {
                    dataOutputStream.writeUTF(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
               setDOStream();
            }
        } else {
            out.printException("Socket not connected! "+socket.getInetAddress().getHostAddress());
        }

    }
}
