package pidev.javafx.Services;

import pidev.javafx.Controller.ConnectionDB;
import pidev.javafx.entities.Transport.Transport;
import pidev.javafx.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class ServicesStation implements IServices{
    Connection cnx = DataSource.GetInstance().getCnx();;
    private PreparedStatement prepare;

    @Override
    public void ajouter(Object o) {
        cnx = ConnectionDB.connectDb();
        String sql = "INSERT INTO stations (NomStation,AddressStation,Type_Vehicule ) VALUES (?,?,?) ";
        try {
            prepare = cnx.prepareStatement(sql);
            prepare.setString(1,"a");
            prepare.setString(2,"aa");
            prepare.setString(3, "Bus");

            prepare.executeUpdate();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void modifier(Object o) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public void getById(int id) {

    }

    @Override
    public Set<Transport> getAll() {
        return null;
    }
}
