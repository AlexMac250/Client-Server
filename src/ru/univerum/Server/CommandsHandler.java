package ru.univerum.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CommandsHandler extends Connection{

    CommandsHandler(Socket socket, Server server) {
        super(socket, server);
    }

    public void handler(String[] message){
        try{
            switch (message[0]){
                case "connection":
                    new DataOutputStream(super.socket.getOutputStream()).writeUTF("connection yes");
                    break;
            }
        } catch (Exception e){

        }
    }



    public String[] rebuildMessage(String message){
        if(!message.equals("")) {
            List<StringBuilder> sl = new ArrayList<>();
            char[] c = message.toCharArray();
            int i = 0;
            sl.add(new StringBuilder());
            for (char ch : c) {
                if (ch == ' ' & !sl.get(sl.size() - 1).toString().equals("")) {
                    i++;
                    sl.add(new StringBuilder());
                } else {
                    sl.get(i).append(ch);
                }
            }
            String s[] = new String[4];
            i = 0;
            for (StringBuilder builder : sl) {
                s[i] = builder.toString();
                i++;
            }
            return s;
        }
        return null;
    }
}
