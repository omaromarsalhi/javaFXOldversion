package pidev.javafx.Controller.Transport;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import pidev.javafx.Controller.ConnectionDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javafx.scene.input.KeyEvent;

public class TranportController {

@FXML
private ListView Transport_list;

@FXML
private TableView Transport_table;


@FXML
private ScrollPane mainBorderPain;


        @FXML
        private TextArea Referance_text;

        @FXML
        private Button showBtn;

        @FXML
        private Button deleteBtn;
        @FXML
        private TableView TableView;

        private Connection connect;

        private PreparedStatement prepare;




    Set<String> resultSetItems = new HashSet<>();
    ObservableList<String> data = show_Transport_ListView() ;
    public TranportController() {
        Transport_list = new ListView<>();
        Transport_table = new TableView<>();
        Transport_table.setItems(data);

    }



    @FXML
        protected void onTextChanged() {
            String[] name = new String[10];

            name[1] = Referance_text.getText();

            if (name[1].matches("[a-zA-Z]+"))
                Referance_text.setStyle("-fx-control-inner-background: #25c12c;");
            else
                Referance_text.setStyle("-fx-control-inner-background: #bb2020;");}

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
//            String sql = "DELETE FROM name WHERE aa = ? ";
//
//            connect = ConnectionDB.connectDb();
//
//            try {
//                prepare = connect.prepareStatement(sql);
//                prepare.setString(1,  Referance_text.getText());
//
//
//                prepare.executeUpdate();
//                System.out.println(  " row(s) deleted.");
//
//            } catch (Exception e) {
////            System.out.println("error");
//                System.out.println(e.getMessage());
//            }

        };

        public ObservableList<String> show_Transport_ListView() {
//            String sql = "SELECT * FROM name";
//            connect = ConnectionDB.connectDb();
//
//            try (
//                    PreparedStatement preparedStatement = connect.prepareStatement(sql);
//                    ResultSet resultSet = preparedStatement.executeQuery()) {
//
//                while (resultSet.next()) {
//
//                    String name = resultSet.getString("aa");
//                    String prename = resultSet.getString("bb");
//                    resultSetItems.add(name);
//                    resultSetItems.add(prename);
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
           return data;
        }


    public void show_Transport_ListView1(KeyEvent event) {
        String keyPressed = event.getCode().toString();

        if(keyPressed.equals("A")) {
            System.out.println(Transport_list.getSelectionModel().getSelectedItem());
            System.out.println(keyPressed);
        }
    }



        public void onVoyageClicked(ActionEvent event) throws IOException {
            ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/AddTransport.fxml")));
            scrollPane.setPrefHeight(mainBorderPain.getPrefHeight()  );
            scrollPane.setPrefWidth( mainBorderPain.getPrefWidth() );
            mainBorderPain.setContent(scrollPane);
        }
        }






