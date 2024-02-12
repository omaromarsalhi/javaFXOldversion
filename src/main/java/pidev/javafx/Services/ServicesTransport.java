package pidev.javafx.Services;


import pidev.javafx.Controller.ConnectionDB;
import pidev.javafx.entities.Transport.Abonnement;
import pidev.javafx.entities.Transport.Transport;
import pidev.javafx.utils.DataSource;

//import javax.swing.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class ServicesTransport implements IServices <Transport> {

    Connection cnx = DataSource.GetInstance().getCnx();
    private PreparedStatement prepare;
    private Set abonnementList;


    @Override
    public void ajouter(Transport transport) {

        String sql = "INSERT INTO transport(Type_Vehicule,Depart,Arivee,Reference,Vehicule_Image,Prix,Heure) VALUES (?,?,?,?,?,?,?) ";

        try {
             prepare = cnx.prepareStatement(sql);
            prepare.setString(1, transport.getType_vehicule());
            prepare.setString(2, transport.getDepart());
            prepare.setString(3, transport.getArivee());
            prepare.setString(4, transport.getReference());
            prepare.setString(5, transport.getVehicule_Image());
            prepare.setFloat(6, transport.getPrix());
            prepare.setTime(7, transport.getHeure());
            prepare.executeUpdate();
            System.out.println("Personne added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Transport transport) {

    }

    @Override
    public void supprimer(int id) {
        String deleteQuery = "DELETE FROM transport WHERE idTransport = ?";
        try {
                prepare = cnx.prepareStatement(deleteQuery);
                prepare.setInt(1, id);
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getById(int id) {

    }
    @Override
    public Set<Transport> getAll() {


     return null;
    }
}
