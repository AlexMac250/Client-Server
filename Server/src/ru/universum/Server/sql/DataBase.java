package ru.universum.Server.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                 System.out.println(resultSet.getInt("id")+", "+resultSet.getString("login")+", "+resultSet.getString("name")+", "+resultSet.getString("email"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
}
