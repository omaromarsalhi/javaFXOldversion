package pidev.javafx.Controller.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;

import java.net.URL;


import javafx.scene.control.cell.PropertyValueFactory;

import pidev.javafx.Model.user.user;

public class listUserController implements Initializable {



    @FXML
    SplitPane splitpane;
    @FXML
    private TableView<user> tableview;

   @FXML
    private TableColumn<String,String> image;

    @FXML
    private TableColumn<user,String> firstname;

    @FXML
    private TableColumn<user,String> lastname;

    @FXML
    private TableColumn<user,String> email;

    @FXML
    private TableColumn<user,Integer> phonenumber;
    ObservableList<user> users = FXCollections.observableArrayList(
            new user("latifa","benzaied","latifa.benzaied@esprit.tn",54066077),
            new user("latifa","benzaied","latifa.benzaied@esprit.tn",54066077),
            new user("latifa","benzaied","latifa.benzaied@esprit.tn",54066077)

    );
   private Image loadImage() {
        return new Image(getClass().getResourceAsStream("/icons/test.png"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        firstname.setCellValueFactory(new PropertyValueFactory<user,String>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<user,String>("lastname"));
        email.setCellValueFactory(new PropertyValueFactory<user,String>("email"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<user,Integer>("num"));
        tableview.setItems(users);
       Image defaultImage = loadImage();
         final ImageView imageView=new ImageView();
        image.setCellFactory(column -> new TableCell<>() {
            private final ImageView imageView = new ImageView(defaultImage);


            @Override
          protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(imageView);
                    imageView.setFitWidth(10);
                    imageView.setFitHeight(10);

                }
            }

        });
        Tooltip tooltip = new Tooltip("Description de l'image");

// Associez le tooltip à l'imageView
        Tooltip.install(imageView, tooltip);

        tableview.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                String selectedItem = String.valueOf(tableview.getSelectionModel().getSelectedItem());
                if (selectedItem != null) {
                    showDetails(selectedItem);
                }
            }
        });

    }



    private void showDetails(String details) {


        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/User/Details.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getRoot().setStyle("-fx-padding: 5px;");
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/style/styleDetails.css")));
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();

            Stage dialogStage = new Stage();
            dialogStage.setScene(scene);
            dialogStage.setTitle("Détails");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initStyle(StageStyle.UNDECORATED);
            DetailsController detailsController = loader.getController();
            detailsController.setScene(scene);
            dialogStage.showAndWait();
        } catch (Exception e) {

            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'affichage de la boîte de dialogue.");
            alert.showAndWait();
        }
    }
}

