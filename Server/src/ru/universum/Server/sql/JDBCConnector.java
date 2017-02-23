package ru.universum.Server.sql;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import ru.universum.Server.Out;

import java.sql.*;

class JDBCConnector {
//   "jdbc:mysql://localhost:3306/serverDB";
    static Out out = new Out("JDBCConnector");

    Connection connection;
    boolean isConnected = false;

    private String URL = "";
    private String USER = "";
    private String PASSWORD = "";

    JDBCConnector(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    void connect(){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            isConnected = true;
            out.printMessage("Connected to DATABASE");
        } catch (SQLException e) {
            System.err.println("Could not connect to database!");
            try {
                if (!connection.isClosed()) disconnect();
            } catch (SQLException e1) {
                e1.printStackTrace();

            }
            isConnected = false;
        }
    }

    void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            isConnected = false;
        }
    }

}
