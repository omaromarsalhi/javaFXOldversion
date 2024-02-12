package pidev.javafx.Services;

import pidev.javafx.entities.Transport.Abonnement;
import pidev.javafx.entities.Transport.Transport;
import pidev.javafx.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ServicesAbonnement implements IServices {

    Connection cnx = DataSource.GetInstance().getCnx();
    private PreparedStatement prepare;
    private Set abonnementList;
    @Override
    public void ajouter(Object o) {

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
    public Set<Abonnement> getAll() {
        Set <Abonnement> abonnementList = new HashSet<>();
        String req = "Select * from abonnement";


        try (PreparedStatement preparedStatement = cnx.prepareStatement(req);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Abonnement abs = new Abonnement();
                abs.setNom(resultSet.getString("Nom"));
                abs.setPrenom(resultSet.getString("Prenom"));
                abs.setType(resultSet.getString("Type_Abonnement"));
                abs.setDateDebut(resultSet.getTimestamp("Date_Debut"));
                abs.setDateFin(resultSet.getDate("Date_Fin"));
                abs.setIdAboonnement(resultSet.getInt("idAbonnement"));
                //System.out.println(abs);

                abonnementList.add(abs);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return abonnementList;


    }
}
