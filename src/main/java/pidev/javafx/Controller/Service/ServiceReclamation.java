package pidev.javafx.Controller.Service;

import pidev.javafx.Controller.Connection.DataSource;
import pidev.javafx.Controller.Entity.Reclamation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ServiceReclamation implements Iservice<Reclamation> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation reclamation) {
        String req = "INSERT INTO `reclamation`(`privateKey`, `subject`, `titre`, `date`, `description`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, reclamation.getPrivateKey());
            ps.setString(2, reclamation.getSubject());
            ps.setString(3, reclamation.getTitre());
            ps.setDate(4, new java.sql.Date(reclamation.getDate().getTime()));
            ps.setString(5, reclamation.getDescription());
            ps.executeUpdate();
            System.out.println("Reclamation added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation reclamation) {
        String req = "UPDATE `reclamation` SET `subject`=?, `titre`=?, `date`=?, `description`=? WHERE `privateKey`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, reclamation.getSubject());
            ps.setString(2, reclamation.getTitre());
            ps.setDate(3, new java.sql.Date(reclamation.getDate().getTime()));
            ps.setString(4, reclamation.getDescription());
            ps.setInt(5, reclamation.getPrivateKey());
            ps.executeUpdate();
            System.out.println("Reclamation updated !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int idReclamation) {
        String req = "DELETE FROM `reclamation` WHERE `idReclamation`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idReclamation);
            ps.executeUpdate();
            System.out.println("Reclamation deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Reclamation getOneById(int idReclamation) {
        Reclamation reclamation = null;
        String req = "SELECT * FROM `reclamation` WHERE `idReclamation`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idReclamation);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                reclamation = new Reclamation(rs.getInt("privateKey"), rs.getString("subject"), rs.getString("titre"), rs.getDate("date"), rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reclamation;
    }

    @Override
    public Set<Reclamation> getAll() {
        Set<Reclamation> reclamations = new HashSet<>();
        String req = "SELECT * FROM `reclamation`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reclamations.add(new Reclamation(rs.getInt("privateKey"), rs.getString("subject"), rs.getString("titre"), rs.getDate("date"), rs.getString("description")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reclamations;
    }
}
