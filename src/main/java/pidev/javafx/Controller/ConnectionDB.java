package pidev.javafx.Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    public static Connection connectDb(){
        Connection connect;
        try{
            connect= DriverManager.getConnection("jdbc:mysql://localhost/pi_dev", "root", "");
            return  connect;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}