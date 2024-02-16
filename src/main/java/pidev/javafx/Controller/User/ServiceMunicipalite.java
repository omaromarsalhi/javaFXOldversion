package pidev.javafx.Controller.User;

import pidev.javafx.Model.user.Municipalite;
import pidev.javafx.utlis.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceMunicipalite implements IserviceMunicipalite<Municipalite> {
    Connection cnx = ConnectionDB.getInstance().getCnx();
    @Override
    public void ajouterMunicipalite(Municipalite municipalite) {

    }

    @Override
    public void modifierMunicipalite(Municipalite municipalite) {

    }

    @Override
    public void supprimerMunicipalite(int id) {

    }

    @Override
    public Municipalite getOneById(int id) {
        return null;
    }

    @Override
    public List<Municipalite> getAll() {
            List<Municipalite> munis = new ArrayList<>();

            String req = "SELECT * FROM `municipalite`";

            try {
                Statement stmt = cnx.createStatement();
                ResultSet rs = stmt.executeQuery(req);


                while (((ResultSet) rs).next()) {
                    Municipalite muni = new Municipalite();
                    muni.setId(rs.getInt("idMunicipalite"));
                    muni.setName(rs.getString("name"));
                    muni.setAdresse(rs.getString("Adresse"));


                    munis.add(muni);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return munis;

        }

    }

