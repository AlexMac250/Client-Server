package ru.univerum.Server;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

class JDBCConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/serverDB";
    private static final String USER = "root";
    private static final String PASSWORD = "rfccbjgtz";

    private static Connection connection;

    static void connect(){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Server.out.printMessage("Соединение с БД "+(!connection.isClosed() ? "" : "не")+"установлено!");


        } catch (SQLException e) {
            Server.out.printError("Не удалось загрузить класс драйвера БД!");
        }

    }

    static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Server.out.printMessage("Соединие с БД разорвано");
    }

}
