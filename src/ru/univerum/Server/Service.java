package ru.univerum.Server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {
    public static List<Port> ports = new ArrayList<>();
    private static String ifases = null;

    static InetAddress getAddress() {
        InetAddress ADDRESS = null;
        ifases = "\nINTERFACES:";
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for(NetworkInterface intf : interfaces) {
                ifases += "\n     "+intf.getName()+": ";
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for(int i = 0; i < addrs.size(); i++){
                    InetAddress ipAddress =addrs.get(i);
                    ifases += ipAddress.getHostAddress()+(i == addrs.size()-1 ? "": ", ");
                    if(!ipAddress.isLoopbackAddress() & !ipAddress.isLinkLocalAddress() & !ipAddress.getHostAddress().contains(":")){
                        ADDRESS = ipAddress;
                        break;
                    }
                }
            }
            ifases += "\n----end of interfaces----";
        }catch (Exception e) {
            e.printStackTrace();;
        }
        return ADDRESS;
    }

    static void setPorts(int fP, int eP){
        for (int i = fP; i < eP; i++) {
            ports.add(new Port(i));
        }
        Server.out.printMessage("Открыты порты с "+fP+" по "+eP);
    }

    public static Port getOpenPort(UserConnection userConnection){
        for (Port port : ports){
            if(port.countConnections < 15){
                port.userConnections[port.userConnections.length-1] = userConnection;
                port.countConnections++;
                System.out.println("Выдан порт: "+port.port);
                return port;
            }
        }
        return null;
    }
}
