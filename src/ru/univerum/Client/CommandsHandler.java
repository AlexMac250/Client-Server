package ru.univerum.Client;

import java.util.ArrayList;
import java.util.List;

public class CommandsHandler {
    public static void handler(String[] message){
        try{
            switch (message[0]){
                case "newport":
                    System.out.println("new port!!");
                    int newport = Integer.parseInt(message[1]);
                    Client.connect(newport);
                    break;

                case "connection":
                    System.out.println("connection: "+message[1]);
                    break;
            }
        } catch (Exception e){

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
        return null;
    }
}
