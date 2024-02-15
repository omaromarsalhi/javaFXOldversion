package service;
import pidev.javafx.entities.Maison;
import pidev.javafx.entities.Voiture;
import utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class Voitureservice implements IServices<Voiture>  {

    Connection cnx = DataSource.getInstance().getCnx();
    private Connection connect;
    private PreparedStatement prepare;

    @Override
    public void ajouter(Voiture voiture) {
        String req = "INSERT INTO `voiture`(`marque`, `modele` , `immatriculation` , `energie` , `circulation` ) " + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,voiture.getMarque());
            ps.setString(2,voiture.getModele());
            ps.setString(3,voiture.getImmatriculation());
            ps.setString(4,voiture.getEnergie());
            ps.setString(5,voiture.getCirculation());
            ps.executeUpdate();
            System.out.println("Voiture added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void modifier(Voiture voiture) {
        try {

            String updateQuery = "UPDATE voiture SET marque = ?," +
                    " modele = ?, immatriculation = ?, energie = ? ,circulation= ?" +
                    "WHERE idvehicule = ?";

            connect = DataSource.getInstance().getCnx();

            prepare = connect.prepareStatement( updateQuery );
            prepare.setString( 1, voiture.getMarque());
            prepare.setString( 2, voiture.getModele() );
            prepare.setString( 3, voiture.getImmatriculation() );
            prepare.setString( 4, voiture.getEnergie() );
            prepare.setString( 5, voiture.getCirculation());
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String deleteQuery = "DELETE FROM voiture WHERE idvehicule = ?";

            connect = DataSource.getInstance().getCnx();

            prepare = connect.prepareStatement( deleteQuery );
            prepare.setInt( 1, id );
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Voiture getoneById(int id) {
        return null;
    }



    @Override
    public List<Voiture> getAll() {
        List<Voiture> voitures = new ArrayList<>();
        try {
            String reqlist = "SELECT * FROM maison";
            Statement s = cnx.createStatement();
            ResultSet r = s.executeQuery(reqlist);
            while (r.next()) {
                Voiture v = new Voiture();
                v.setMarque(r.getString("marque"));
                v.setModele(r.getString("modele"));
                v.setImmatriculation(r.getString("Immatriculation"));
                v.setEnergie(r.getString("energie"));
                v.setCirculation(r.getString("circulation"));
                voitures.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); //
        }

        return voitures;
    }

    @Override
    public List<Maison> affiche() {
        return null;
    }


}

