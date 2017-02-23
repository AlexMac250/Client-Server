package ru.universum.Server;

/**
 * Created by Aleksandr on 23.02.17.
 */
public class Out {

    String parent = "";

    public Out(String parent) {
        this.parent = parent;
    }

    public void printMessage(String string){
        System.out.println("[СООБЩЕНИЕ]("+parent+"): " + string);
    }
    public void printException(String exception){
        System.err.println("[ИСКЛЮЧИНИЕ]("+parent+"):  " + exception);
    }
    public void printWarning(String warning){
        System.out.println("[ВНИМАНИЕ]("+parent+"): "+ warning);
    }
    public void printError(String error){
        System.err.println("![ОШИБКА]("+parent+"): "+error);
    }
}
