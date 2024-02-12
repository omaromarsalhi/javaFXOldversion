package pidev.javafx.Controller.Transport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import pidev.javafx.Controller.ConnectionDB;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

public class StationController {


    @FXML
   private TextArea Referance_text;



    @FXML
    private Button showBtn;

    @FXML
    private Button deleteBtn;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;


    @FXML
    private ScrollPane mainBorderPain;
@FXML
    protected void onTextChanged() {
    String[] name = new String[10];

    name[1] = Referance_text.getText();

    if (name[1].matches("[a-zA-Z]+"))
        Referance_text.setStyle("-fx-control-inner-background: #25c12c;");
    else
        Referance_text.setStyle("-fx-control-inner-background: #bb2020;");}
//
//    if (  name[2].matches("[(a-zA-Z)|(1-9)]+"))
//        Name_area1.setStyle("-fx-control-inner-background: #25c12c;");
//    else
//        Name_area1.setStyle("-fx-control-inner-background: #bb2020;");
//
//
//    if (  name[3].matches("[a-zA-Z]+"))
//        Name_area2.setStyle("-fx-control-inner-background: #25c12c;");
//    else
//        Name_area2.setStyle("-fx-control-inner-background: #bb2020;");
//
//    if (  name[4].matches("[a-zA-Z]+"))
//        Name_area3.setStyle("-fx-control-inner-background: #25c12c;");
//    else
//        Name_area3.setStyle("-fx-control-inner-background: #bb2020;");
//
//
//    if (  name[5].matches("[a-zA-Z]+"))
//        Name_area4.setStyle("-fx-control-inner-background: #25c12c;");
//    else
//        Name_area4.setStyle("-fx-control-inner-background: #bb2020;");
//      }

@FXML
      public void afficher(){
    String sql = "INSERT INTO name "
                    + "(aa,bb) "
                    + "VALUES(?,?)";

            connect = ConnectionDB.connectDb();

            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1,  Referance_text.getText());
                prepare.setString(2,  Referance_text.getText());

                prepare.executeUpdate();

            } catch (Exception e) {
//            System.out.println("error");
                System.out.println(e.getMessage());
            }

      };

    @FXML
    public void supprimer(){
        String sql = "DELETE FROM name WHERE aa = ? ";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1,  Referance_text.getText());


            prepare.executeUpdate();
            System.out.println(  " row(s) deleted.");

        } catch (Exception e) {
//            System.out.println("error");
            System.out.println(e.getMessage());
        }

    };

    public void onInsertStationClicked(ActionEvent event)  throws IOException {
        ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/AddStation.fxml")));
        scrollPane.setPrefHeight(mainBorderPain.getPrefHeight()  );
        scrollPane.setPrefWidth( mainBorderPain.getPrefWidth() );
        mainBorderPain.setContent(scrollPane);
    };

}


