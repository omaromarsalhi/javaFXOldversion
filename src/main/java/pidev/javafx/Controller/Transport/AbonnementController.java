package pidev.javafx.Controller.Transport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pidev.javafx.Controller.ConnectionDB;
import pidev.javafx.entities.Transport.Abonnement;
import pidev.javafx.entities.Transport.Transport;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbonnementController implements Initializable {

    private Connection connect;

    ObservableList<Transport> dataList = FXCollections.observableArrayList();
    List<Abonnement> abonnementList = new ArrayList<>();


    @FXML
    private Label DebutLabel;

    @FXML
    private Label FinLabel;

    @FXML
    private Label IdLabel;

    @FXML
    private Label NomLabel;

    @FXML
    private Label PrenomLabel;
    @FXML
            private Button nextBtn;
    @FXML
            private Button previousBtn;
       int i;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
afficher();
        remplir_abonnement();
    }

    @FXML
    public void afficher() {
        String sql = "SELECT * FROM Abonnement";
        connect = ConnectionDB.connectDb();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql);
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


            System.out.println(abonnementList.get(0).toString());
            System.out.println(abonnementList.get(1).toString());
            System.out.println(abonnementList.get(2).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public  void remplir_abonnement(){
        if (i==0)
            previousBtn.setVisible(false);
        else if (i==abonnementList.size()-1) {
            nextBtn.setVisible(false);
        }
String [] time=abonnementList.get(i).getDateDebut().toLocalDateTime().toString().split("T");
String id=Integer.toString(abonnementList.get(i).getIdAboonnement());
         DebutLabel.setText(time[0]);
        FinLabel.setText(abonnementList.get(i).getDateFin().toString());
        NomLabel.setText(abonnementList.get(i).getNom());
        PrenomLabel.setText(abonnementList.get(i).getPrenom());
        IdLabel.setText("000"+id);



    }
    public void nextAb(){

        if(i < abonnementList.size()-1)
        {
            nextBtn.setVisible(true);
            previousBtn.setVisible(true);
            i=i+1;
            System.out.println(i);
            remplir_abonnement();
    }
        else  {
            nextBtn.setVisible(false);
            previousBtn.setVisible(true);
        }
    }
    public void previousAb(){

        if(i > 0 )
        {
            previousBtn.setVisible(true);
            nextBtn.setVisible(true);
            i=i-1;
            System.out.println(i);
            remplir_abonnement();
        }
        else if (i==0){
            previousBtn.setVisible(false);
            nextBtn.setVisible(true);
        }
    }


}
