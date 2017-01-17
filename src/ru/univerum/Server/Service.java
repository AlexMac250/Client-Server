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
    @SuppressWarnings("unused")
    public static InetAddress getAddress(String address) {
        InetAddress ADDRESS = null;
        try {
            ADDRESS = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ADDRESS;
    }

    static void setPorts(int fP, int eP){
        for (int i = fP; i < eP; i++) {
            ports.add(new Port(i));
        }
        Server.out.printMessage("ports created");
    }
    public static Port getOpenPort(Connection connection){
        for (Port port : ports){
            if(port.connections.length < 15000){
                port.connections[port.connections.length-1] = connection;
                port.countConnections++;
                System.out.println("issued port: "+port.port);
                return port;
            }
        }
        return null;
    }
}
