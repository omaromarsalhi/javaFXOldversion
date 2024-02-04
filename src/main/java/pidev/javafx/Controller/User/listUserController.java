package pidev.javafx.Controller.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.animation.FadeTransition;

public class listUserController implements Initializable {
    @FXML
    ListView<String> listview;



    @FXML
    SplitPane splitpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listview.getItems().addAll("latifa","omar");
        listview.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                String selectedItem = listview.getSelectionModel().getSelectedItem();
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
           scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/styleLogin.css") ) );
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
