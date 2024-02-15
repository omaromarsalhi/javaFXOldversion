package service;

import pidev.javafx.entities.Maison;
import pidev.javafx.entities.Voiture;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Maisonsservice implements IServices<Maison> {

    Connection c;

    public Maisonsservice(){
        c=DataSource.getInstance().getCnx();
    }


    @Override
    public void ajouter(Maison maison) {
        try {
            String req ="insert into maison(nomprop,adresse,numtelprop) VALUES(?,?,?)";

            PreparedStatement ps = c.prepareStatement(req);
            ps.setString(1,maison.getNomprop());
            ps.setString(2,maison.getAdresse());
            ps.setInt(3,maison.getNumtelprop());
            ps.executeUpdate();
            System.out.println("Maison added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Maison maison) {
        try {
            String requpdate = "UPDATE maison SET nomprop = ?," +
                    "adresse = ?, numtelprop = ?" +
                    "WHERE idmaison = ?";

            PreparedStatement p = c.prepareStatement(requpdate);
            p.setInt(1, maison.getIdmaison());
            p.setString(2, maison.getNomprop());
            p.setString(3, maison.getAdresse());
            p.setInt(4, maison.getNumtelprop());

            p.executeUpdate();
            System.out.println("Maison modifiée !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String deleteQuery = "DELETE FROM maison WHERE idmaison = ?";

            c= DataSource.getInstance().getCnx();
            PreparedStatement p = c.prepareStatement(deleteQuery);
            p = c.prepareStatement( deleteQuery );
            p.setInt( 1, id );
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Maison getoneById(int id) {
        return null;
    }

    @Override
    public List<Voiture> getAll() {
        return null;
    }

    @Override
    public List<Maison> affiche() {
        List<Maison> list = new ArrayList<>();
        try {
            String reqlist = "SELECT * FROM maison";
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(reqlist);
            while (r.next()) {
                Maison m = new Maison();
                m.setIdmaison(r.getInt("idmaison"));
                m.setNomprop(r.getString("nomprop"));
                m.setAdresse(r.getString("adresse"));
                m.setNumtelprop(r.getInt("numtelprop"));
                list.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); //
        }
        return list;
    }
}
