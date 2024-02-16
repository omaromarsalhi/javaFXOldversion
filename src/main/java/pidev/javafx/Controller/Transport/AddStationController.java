package pidev.javafx.Controller.Transport;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import pidev.javafx.Services.ServicesStation;
import pidev.javafx.entities.Transport.Station;
import pidev.javafx.entities.Transport.Type_Vehicule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
    String  Address;



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
        String nomStation = NomStationText.getText();
        String address = AdressText.getText();  // Replace with the actual address or get it from a control

        Station st = new Station(nomStation, address, BoxTypeVehicule.getValue().toString());
        ss.ajouter(st);

        Pane successPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Transport/added_succesfully.fxml")));

        successPane.setPrefHeight(mainBorderPain.getPrefHeight());
        successPane.setPrefWidth(mainBorderPain.getPrefWidth());
        mainBorderPain.setContent(successPane);

        PauseTransition pause = new PauseTransition(Duration.seconds(2.33));
        pause.setOnFinished(event -> {
            try {
                ScrollPane anotherPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Transport/Station.fxml")));
                anotherPane.setPrefHeight(mainBorderPain.getPrefHeight());
                anotherPane.setPrefWidth(mainBorderPain.getPrefWidth());
                mainBorderPain.setContent(anotherPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }


    void mapView(){


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Load_types();
    }
}
