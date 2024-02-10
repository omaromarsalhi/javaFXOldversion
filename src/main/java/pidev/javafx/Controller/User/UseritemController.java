package pidev.javafx.Controller.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pidev.javafx.Model.user.User;

import java.net.URL;
import java.util.ResourceBundle;

public class UseritemController implements Initializable {
    @FXML
    private ImageView image;
    @FXML
    private Label firstname;

    @FXML
    private Label lastname;

    @FXML
    private Label email;
    public void setData(User user1){
        firstname.setText(user1.getFirstname());
        lastname.setText(user1.getLastname());
        email.setText(user1.getEmail());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
@FXML
    public void btnmodifier(ActionEvent actionEvent) {
        System.out.println("helooooooooooooooooo");
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Details.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);


            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   /* private void showDetails() {


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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'affichage de la boîte de dialogue.");
            alert.showAndWait();
        }
    }*/

}
