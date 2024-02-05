package pidev.javafx.Controller.Transport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import pidev.javafx.Controller.ConnectionDB;
import pidev.javafx.Model.Transport.Transport;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

 public class TranportController implements Initializable  {

@FXML
private ListView Transport_list;

@FXML
private TableView Transport_table;


@FXML
private ScrollPane mainBorderPain;









        private Connection connect;

        private PreparedStatement prepare;


    @FXML
    private TableColumn<Transport, Date> ArriveCol;

    @FXML
    private TableColumn<Transport, Date> DepartCol;

    @FXML
    private TableColumn<Transport, String> TypeCol;

    @FXML
    private TableColumn<Transport, Date> HeureCol;

    @FXML
    private TableColumn<Transport, Float> PrixCol;

    @FXML
    private TableColumn<Transport, String> ReferenceCol;
     private TableColumn<Transport, Void> FunctionsCol;





    Set<String> resultSetItems = new HashSet<>();
    private Transport data  =new Transport();
    private URL url;
    private ResourceBundle resourceBundle;
     FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Transport/Buttons/Delete.fxml"));
     Button btn = new Button();


int selectedRow=0;

     @Override
     public void initialize(URL url, ResourceBundle resourceBundle) {
         afficher();
         btn.setStyle("-fx-background-color: #bb2020; " +
                 "-fx-text-fill: white; -fx-font-size: 14px;" + "-fx-border-radius: 15px;"+
                 " -fx-padding: 10 20;");
         TableColumn<Transport, Void> functionCol = new TableColumn<>("");

         functionCol.setCellFactory( new Callback<TableColumn<Transport, Void>, TableCell<Transport, Void>>() {
                     @Override
                     public TableCell<Transport, Void> call(TableColumn<Transport, Void> param) {
                         return new TableCell<Transport, Void>() {
                              Button btn = new Button();

                             {
                                 btn.setOnAction((ActionEvent event) -> {
                                      Transport data = getTableView().getItems().get(getIndex());
                                      selectedRow= data.getIdTransport();
                                     Supprimer(selectedRow);
                                     afficher();
                                   });
                             }

                             @Override
                             protected void updateItem(Void item, boolean empty) {
                                 super.updateItem(item, empty);
                                 if (empty) {
                                     setGraphic(null);
                                 } else {
                                     setGraphic(btn);
                                 }
                             }
                         };
                     }
                 } );

         TableColumn<Transport, Void> functionCol_ = new TableColumn<>("");
         functionCol_.setCellFactory( new Callback<TableColumn<Transport, Void>, TableCell<Transport, Void>>() {
             @Override
             public TableCell<Transport, Void> call(TableColumn<Transport, Void> param) {
                 return new TableCell<Transport, Void>() {



                     {
                         btn.setOnAction((ActionEvent event) -> {
                             Transport data = getTableView().getItems().get(getIndex());
                             selectedRow= data.getIdTransport();
                             Supprimer(selectedRow);
                             afficher();
                         });
                     }

                     @Override
                     protected void updateItem(Void item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty) {
                             setGraphic(null);
                         } else {
                             setGraphic(btn);
                         }
                     }
                 };
             }
         } );

         Transport_table.getColumns().add(functionCol);
         Transport_table.getColumns().add(functionCol_);

     }




     @FXML
     public void afficher() {
         String sql = "SELECT * FROM transport";
         connect = ConnectionDB.connectDb();

         try (PreparedStatement preparedStatement = connect.prepareStatement(sql);
              ResultSet resultSet = preparedStatement.executeQuery()) {

             // Create an ObservableList to store your data
             ObservableList<Transport> dataList = FXCollections.observableArrayList();

             while (resultSet.next()) {
                 Transport data = new Transport();

                 data.setIdTransport(Integer.parseInt(resultSet.getString("idTransport")));
                 data.setType_vehicule(resultSet.getString("Type_Vehicule"));
                 data.setReference(resultSet.getString("Reference"));
                 data.setDepart(resultSet.getString("Depart"));
                 data.setArivee(resultSet.getString("Arivee"));
                 data.setPrix((resultSet.getFloat("Prix")));
                 data.setHeure((resultSet.getTime("Heure")));

                 dataList.add(data);
             }




             ReferenceCol.setCellValueFactory(new PropertyValueFactory<>("Reference"));
             HeureCol.setCellValueFactory(new PropertyValueFactory<>("Heure"));
             DepartCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
             ArriveCol.setCellValueFactory(new PropertyValueFactory<>("arivee"));
             TypeCol.setCellValueFactory(new PropertyValueFactory<>("type_vehicule"));
             PrixCol.setCellValueFactory(new PropertyValueFactory<>("Prix"));




             Transport_table.setItems(dataList);

         } catch (Exception e) {
             System.out.println(e.getMessage());
         }

         System.out.println("Data loaded into TableView");
     }


        public void update() {
            String sql = "UPDATE  transport SET   WHERE ";
            connect = ConnectionDB.connectDb();

            try (PreparedStatement preparedStatement = connect.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Create an ObservableList to store your data
                ObservableList<Transport> dataList = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    Transport data = new Transport();

                    data.setIdTransport(Integer.parseInt(resultSet.getString("idTransport")));
                    data.setType_vehicule(resultSet.getString("Type_Vehicule"));
                    data.setReference(resultSet.getString("Reference"));
                    data.setDepart(resultSet.getString("Depart"));
                    data.setArivee(resultSet.getString("Arivee"));
                    data.setPrix((resultSet.getFloat("Prix")));
                    data.setHeure((resultSet.getTime("Heure")));

                }


        Transport_table.setItems(dataList);

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println("Data loaded into TableView");
}




@FXML
        public Boolean Supprimer(int id) {       // Delete from the database
            String deleteQuery = "DELETE FROM transport WHERE idTransport = ?";
            try (Connection connection = ConnectionDB.connectDb();
                 PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }


    public void show_Transport_ListView1(KeyEvent event) {
        String keyPressed = event.getCode().toString();

        if(keyPressed.equals("A")) {
            System.out.println(Transport_list.getSelectionModel().getSelectedItem());
            System.out.println(keyPressed);
        }
    }



        public void onInsertClicked(ActionEvent event) throws IOException {
            ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/AddTransport.fxml")));
            scrollPane.setPrefHeight(mainBorderPain.getPrefHeight()  );
            scrollPane.setPrefWidth( mainBorderPain.getPrefWidth() );
            mainBorderPain.setContent(scrollPane);
        }

     public void onInsertStationClicked(ActionEvent event)  throws IOException {
         ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/AddStation.fxml")));
         scrollPane.setPrefHeight(mainBorderPain.getPrefHeight()  );
         scrollPane.setPrefWidth( mainBorderPain.getPrefWidth() );
         mainBorderPain.setContent(scrollPane);
     };
     


}






