package pidev.javafx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    Connection cnx;
    public DataSource(){
        try {
            String URL = "jdbc:mysql://localhost:3306/esprit/";
            String USER = "root";
            String PWD = "";
            cnx= DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
