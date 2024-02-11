package pidev.javafx.Services;

import pidev.javafx.entities.Transport.Transport;
import pidev.javafx.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class ServicesTransport implements IServices <Transport> {

    Connection cnx = (Connection) DataSource.GetInstance();


    @Override
    public void ajouter(Transport transport) {
        String req = "INSERT INTO `personne`(`nom`, `prenom`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,transport.getArivee());
            ps.setString(2,transport.getReference());
            ps.executeUpdate();
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

    }

    @Override
    public void getById(int id) {

    }
    @Override
    public Set<Transport> getAll() {
        Set<Transport> Transport = new HashSet<>();

        String req = "Select * from transport";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                int id = res.getInt("id");
                String nom = res.getString(2);
                String prenom = res.getString("prenom");
                Transport p = new Transport();
                Transport.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Transport;
    }
}
