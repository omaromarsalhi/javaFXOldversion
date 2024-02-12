package pidev.javafx.Controller.Transport;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import pidev.javafx.Services.ServicesStation;
import pidev.javafx.entities.Transport.Station;
import pidev.javafx.entities.Transport.Type_Vehicule;
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

    ServicesStation ss=new ServicesStation();



    @FXML
    protected void onTextChanged() {
        String[] text = new String[10];

        text[1] = NomStationText.getText();
       // text[2]= PrixText.getText();

        if (text[1].matches("[a-zA-Z]*"))
            NomStationText.setStyle("-fx-text-fill: #25c12c;");
        else
            NomStationText.setStyle("-fx-text-fill: #bb2020;");


    }

    @FXML
    protected  void Load_types() {
        if(BoxTypeVehicule.getValue()==null)
            BoxTypeVehicule.getItems().addAll( Type_Vehicule.values());
    }

@FXML
protected void insertStation() throws IOException {

        String nomStation=NomStationText.getText();
        String adress=AdressText.getText();

      Station st= new Station(nomStation,adress,BoxTypeVehicule.getValue().toString());
       ss.ajouter(st);

        Pane scrollPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Transport/added_succesfully.fxml")));
        scrollPane.setPrefHeight(mainBorderPain.getPrefHeight());
        scrollPane.setPrefWidth(mainBorderPain.getPrefWidth());
        mainBorderPain.setContent(scrollPane);

        //  showDialog();


}




    void mapView(){


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Load_types();
    }
}
