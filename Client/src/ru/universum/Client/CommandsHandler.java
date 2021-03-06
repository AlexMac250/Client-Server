package ru.universum.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommandsHandler {
    public static void handler(String[] message){
        try{
            switch (message[0]){
                case "newport":
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("new port!!");
                    int newport = Integer.parseInt(message[1]);
                    Connection.reconnect(newport);
                    break;

                case "connection":
                    if (Boolean.parseBoolean(message[1])){
                        Client.isConnected = true;
                    }
                    break;

                case "":
            }
            System.out.println("---->> "+message[0]+" "+message[1]+" "+message[2]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("Duplicates")
    static String[] rebuildMessage(String message){
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
        return new String[3];
    }
}
