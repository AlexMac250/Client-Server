package ru.universum.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UserConnection extends Thread{
    Port port;
    Socket socket;
    DataOutputStream dataOutputStream;
    InputReader inputReader;
    CommandsHandler commandsHandler;
    Server server;


    UserConnection(Socket socket, Server server){
        Server.out.printMessage("Новое подключение");
        try {
            this.server = server;
            this.socket = socket;
            inputReader = new InputReader(socket, this);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            setNewPort();
            start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputReader.close();
                dataOutputStream.close();
                socket.close();
            } catch (IOException ignored) {
                Server.out.printException("Иключение при закрытии соединения");
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
            dataOutputStream.writeUTF("newport "+newport.port);
            port = newport;
            inputReader.close();
            dataOutputStream.close();
            socket = new ServerSocket(newport.port, 2, server.ADDRESS).accept();
            setDOStream();
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
            Server.out.printException("Socket not connected! "+socket.getInetAddress().getHostAddress());
        }

    }
}
