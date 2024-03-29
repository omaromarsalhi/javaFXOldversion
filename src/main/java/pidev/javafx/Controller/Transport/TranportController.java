package pidev.javafx.Controller.Transport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import pidev.javafx.Controller.ConnectionDB;
import pidev.javafx.Services.ServicesTransport;
import pidev.javafx.entities.Transport.Transport;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


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
    @FXML
    private TableColumn<Transport, String> ImageCol;
     TableColumn<Transport, Void> functionCol = new TableColumn<>("");

@FXML
    TextField SearchText;



     Set<String> resultSetItems = new HashSet<>();
    private Transport data  =new Transport();
    private URL url;
    private ResourceBundle resourceBundle;
     FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Transport/Buttons/Delete.fxml"));
     Button btn = new Button();

Transport t;
     int selectedRow=0;
     ServicesTransport st=new ServicesTransport();



     @FXML
     public void initialize(URL url, ResourceBundle resourceBundle) {
         afficher();
         btn.setStyle("-fx-background-color: #bb2020; " +
                 "-fx-text-fill: white; -fx-font-size: 14px;" + "-fx-border-radius: 15px;" + "-fx-background-color: transparent");

         functionCol.setCellFactory(new Callback<TableColumn<Transport, Void>, TableCell<Transport, Void>>() {
             @Override
             public TableCell<Transport, Void> call(TableColumn<Transport, Void> param) {
                 return new TableCell<Transport, Void>() {
                     HBox hbox = new HBox();

                     {
                         Button Deletebtn = new Button();
                         Button Updatebtn = new Button();
                         ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/Delete-button.svg.png")));
                         ImageView updateIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/EditIcon.png")));

                         deleteIcon.setFitWidth(20); // Set your desired width
                         deleteIcon.setFitHeight(20);
                         updateIcon.setFitWidth(20); // Set your desired width
                         updateIcon.setFitHeight(20);
                         Deletebtn.setGraphic(deleteIcon);
                       Deletebtn.setStyle("-fx-background-color: transparent");
                       Updatebtn.setGraphic(updateIcon);
                       Updatebtn.setStyle("-fx-background-color: transparent");


                         Deletebtn.setOnAction((ActionEvent event) -> {
                             Transport data = getTableView().getItems().get(getIndex());
                             selectedRow = data.getIdTransport();

                             Supprimer(selectedRow);
                             afficher();
                         });

                         Updatebtn.setOnAction((ActionEvent event) -> {
                             Transport data = getTableView().getItems().get(getIndex());
                             selectedRow = data.getIdTransport();
                             t=data;


                             try{
                                 onUpdateButtonClicked(t);

                            }

                          catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                         });

                         hbox.getChildren().addAll(Deletebtn, Updatebtn);
                     }

                     @Override
                     protected void updateItem(Void item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty) {
                             setGraphic(null);
                         } else {
                             setGraphic(hbox);
                         }
                     }
                 };
             }
         });

         Transport_table.getColumns().add(functionCol);

     }




     @FXML
     public void afficher() {


        // dataList=st.getAll();




Set<Transport> dataList=new HashSet<>();

             ReferenceCol.setCellValueFactory(new PropertyValueFactory<>("Reference"));
             HeureCol.setCellValueFactory(new PropertyValueFactory<>("Heure"));
             DepartCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
             ArriveCol.setCellValueFactory(new PropertyValueFactory<>("arivee"));
             TypeCol.setCellValueFactory(new PropertyValueFactory<>("type_vehicule"));
             PrixCol.setCellValueFactory(new PropertyValueFactory<>("Prix"));
              ImageCol.setCellValueFactory(new PropertyValueFactory<>("vehicule_Image"));

             // Set the cell factory for rendering the image
             ImageCol.setCellFactory(param -> new TableCell<>() {
                 private final ImageView imageView = new ImageView();

                 {
                     imageView.setFitWidth(50);
                     imageView.setFitHeight(50);
                 }

                 @Override
                 protected void updateItem(String imagePath, boolean empty) {
                     super.updateItem(imagePath, empty);

                     if (empty || imagePath == null) {
                         setGraphic(null);
                     } else {
                         // Set the image for the ImageView
                         Image image = new Image(imagePath);
                         imageView.setImage(image);
                         imageView.setStyle("-fx-border-radius: 12px; -fx-background-color: blue;");
                         setGraphic(imageView);
                     }
                 }
             });
             dataList=st.getAll();
         ObservableList<Transport> observableList = FXCollections.observableArrayList(dataList);

             Transport_table.setItems(observableList);



      }
//
//      public void Recherche(){
//
//
//          String sql = "SELECT * FROM transport WHERE Type_Vehicule=?  ";
//
//          connect = ConnectionDB.connectDb();
//
//          System.out.println(SearchText.getText());
//          try (PreparedStatement preparedStatement = connect.prepareStatement(sql);
//
//               ResultSet resultSet = preparedStatement.executeQuery()) {
//              prepare = connect.prepareStatement(sql);
//              prepare.setString(1,SearchText.getText());
//
//              // Create an ObservableList to store your data
//              ObservableList<Transport> dataList = FXCollections.observableArrayList();
//
//              while (resultSet.next()) {
//                  Transport data = new Transport();
//
//                  data.setIdTransport(Integer.parseInt(resultSet.getString("idTransport")));
//                  data.setType_vehicule(resultSet.getString("Type_Vehicule"));
//                  data.setReference(resultSet.getString("Reference"));
//                  data.setDepart(resultSet.getString("Depart"));
//                  data.setArivee(resultSet.getString("Arivee"));
//                  data.setPrix((resultSet.getFloat("Prix")));
//                  data.setHeure((resultSet.getTime("Heure")));
//                  data.setVehicule_Image((resultSet.getString("Vehicule_Image")));
//
//                  dataList.add(data);
//
//              }
//
//
//  List<String> nameList = dataList.stream()
//           .map(Transport::getArivee)
//           .collect(Collectors.toList());
//              nameList.stream().anyMatch( str -> str.equals(SearchText.getText()));
//
//              ReferenceCol.setCellValueFactory(new PropertyValueFactory<>("Reference"));
//              HeureCol.setCellValueFactory(new PropertyValueFactory<>("Heure"));
//              DepartCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
//              ArriveCol.setCellValueFactory(new PropertyValueFactory<>("arivee"));
//              TypeCol.setCellValueFactory(new PropertyValueFactory<>("type_vehicule"));
//              PrixCol.setCellValueFactory(new PropertyValueFactory<>("Prix"));
//              ImageCol.setCellValueFactory(new PropertyValueFactory<>("vehicule_Image"));
//
//              // Set the cell factory for rendering the image
//              ImageCol.setCellFactory(param -> new TableCell<>() {
//                  private final ImageView imageView = new ImageView();
//
//                  {
//                      imageView.setFitWidth(50);
//                      imageView.setFitHeight(50);
//                  }
//
//                  @Override
//                  protected void updateItem(String imagePath, boolean empty) {
//                      super.updateItem(imagePath, empty);
//
//                      if (empty || imagePath == null) {
//                          setGraphic(null);
//                      } else {
//                          // Set the image for the ImageView
//                          Image image = new Image(imagePath);
//                          imageView.setImage(image);
//                          imageView.setStyle("-fx-border-radius: 12px; -fx-background-color: blue;");
//                          setGraphic(imageView);
//                      }
//                  }
//              });
//
//              Transport_table.setItems(dataList);
//
//          } catch (Exception e) {
//              System.out.println(e.getMessage());
//          }
//      }
//





        @FXML
        public Boolean Supprimer(int id) {       // Delete from the database
        st.supprimer(id);
        return true;
        };






        public void onInsertClicked(ActionEvent event) throws IOException {
            ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/AddTransport.fxml")));
            scrollPane.setPrefHeight(mainBorderPain.getPrefHeight()  );
            scrollPane.setPrefWidth( mainBorderPain.getPrefWidth() );
            mainBorderPain.setContent(scrollPane);
        }


     public void onUpdateButtonClicked(Transport t)  throws IOException {
         UpdateTransport.setData(t);
         ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/UpdateTransport.fxml")));
          scrollPane.setPrefHeight(mainBorderPain.getPrefHeight()  );
         scrollPane.setPrefWidth( mainBorderPain.getPrefWidth() );
         mainBorderPain.setContent(scrollPane);
     };

     


}






