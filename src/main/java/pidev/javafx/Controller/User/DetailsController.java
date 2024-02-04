package pidev.javafx.Controller.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;

public class DetailsController  implements Initializable {
@FXML
private Button fermer;
    @FXML
    private AnchorPane rootPane;
    @FXML

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
@FXML
    public void btnonclick(ActionEvent actionEvent) {

        scene.getWindow().hide();

    }
}
