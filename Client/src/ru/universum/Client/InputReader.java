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
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        System.out.println("InputReader started");
        try{
            while (!interrupted()){
                String message;
                if (!socket.isClosed()){
                    message = dataInputStream.readUTF();
                    String[] message2 = rebuildMessage(message);
                    CommandsHandler.handler(message2);
                }
            }
        } catch (IOException e) {
            System.err.println("InputReader has crashed (Socket: "+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+")");
            e.printStackTrace();
            interrupt();
            return;
        } finally {
            interrupt();
            Client.isConnected = false;
        }
    }
    private void read(){

    }

    public void CLOSE(){
        interrupt();
    }
}
