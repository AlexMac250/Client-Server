package ru.universum.Server.sql;

import java.sql.*;

public class DataBase {
    JDBCConnector connector;
    Statement statement;
    protected String URL = "";
    protected String USER = "";
    protected String PASSWORD = "";

    public DataBase(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        connector = new JDBCConnector(URL, USER, PASSWORD);
        connector.connect();
        try {
            statement = connector.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public void printAll(){
         try {
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
             while (resultSet.next()){
                 System.out.println("\nID: "+resultSet.getInt("id")+"\nLOGIN: "+resultSet.getString("login")+"\nNAME: "+resultSet.getString("name")+"\nEMAIL: "+resultSet.getString("email")+"\n");
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
}
