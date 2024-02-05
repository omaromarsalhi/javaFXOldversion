package pidev.javafx.Controller.Transport;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import pidev.javafx.Controller.ConnectionDB;
import pidev.javafx.Model.Transport.Type_Vehicule;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class AddStationController implements Initializable {
    @FXML
    private ComboBox BoxTypeVehicule;

    @FXML
    private TextField NomStationText;

    @FXML
    private TextField AdressText;

    @FXML
    private Pane Pane;

    @FXML
    private ScrollPane mainBorderPain;
    private Connection connect;

    private PreparedStatement prepare;




    @FXML
    protected void onTextChanged() {
        String[] text = new String[10];

        text[1] = NomStationText.getText();
       // text[2]= PrixText.getText();

        if (text[1].matches("[a-zA-Z]*"))
            NomStationText.setStyle("-fx-text-fill: #25c12c;");
        else
            NomStationText.setStyle("-fx-text-fill: #bb2020;");

//        if (text[2].matches("[0-9]+"))
//            PrixText.setStyle("-fx-text-fill: #25c12c");
//        else
//            PrixText.setStyle("-fx-text-fill: #bb2020 ");
    }

    @FXML
    protected  void Load_types() {
        if(BoxTypeVehicule.getValue()==null)
            BoxTypeVehicule.getItems().addAll( Type_Vehicule.values());
    }

@FXML
protected void insertStation(){

        String nomStation=NomStationText.getText();
        String adress=AdressText.getText();
       // String TypeVehicule=BoxTypeVehicule.getValue().toString();

    connect = ConnectionDB.connectDb();
    String sql = "INSERT INTO stations (NomStation,AddressStation,Type_Vehicule ) VALUES (?,?,?) ";
    try {
        prepare = connect.prepareStatement(sql);
        prepare.setString(1,nomStation);
        prepare.setString(2,adress);
        prepare.setString(3, "Bus");

        prepare.executeUpdate();


        Pane scrollPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Transport/added_succesfully.fxml")));
        scrollPane.setPrefHeight(mainBorderPain.getPrefHeight());
        scrollPane.setPrefWidth(mainBorderPain.getPrefWidth());
        mainBorderPain.setContent(scrollPane);

        //  showDialog();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        System.out.println("Error inserting data.");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}




    void mapView(){


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Load_types();
    }
}
