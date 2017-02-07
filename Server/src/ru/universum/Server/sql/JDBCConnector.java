package ru.universum.Server.sql;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.*;

class JDBCConnector {
//   "jdbc:mysql://localhost:3306/serverDB";

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
        } catch (SQLException e) {
            System.err.println("Could not load database driver class!");
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
