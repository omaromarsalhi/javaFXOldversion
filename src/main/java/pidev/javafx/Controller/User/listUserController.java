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
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/User/Account.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Créer une nouvelle fenêtre de dialogue modale
            Stage dialogStage = new Stage();
            dialogStage.setScene(scene);
            dialogStage.setTitle("Détails");
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            // Afficher la fenêtre de dialogue
            dialogStage.showAndWait();
        } catch (Exception e) {
            // Gérer les erreurs
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'affichage de la boîte de dialogue.");
            alert.showAndWait();
        }
    }
}
