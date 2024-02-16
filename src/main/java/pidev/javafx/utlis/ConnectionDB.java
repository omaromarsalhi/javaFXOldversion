package pidev.javafx.utlis;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionDB {
    private Connection cnx;

    private static ConnectionDB instance;
    private ConnectionDB(){
        Connection connect;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx= DriverManager.getConnection("jdbc:mysql://localhost/pi_dev", "root", "");
            System.out.println("connected sucessfuly");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static ConnectionDB getInstance(){
        if(instance == null)
            instance = new ConnectionDB();
        return instance;
    }

    public Connection getCnx(){
        return this.cnx;
    }


}
