package pidev.javafx.Controller.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.net.URL;
import java.security.PrivateKey;
import java.util.ResourceBundle;
public class loginController {
    @FXML
  private   ImageView imageview;
    @FXML
    private BorderPane borderpane;
    @FXML
    private TextField email;
    @FXML
    private TextField mp;
    @FXML
    private VBox vbox;
    @FXML
    private  Button login;
    @FXML
    private Line line;
    @FXML
    private  Button btnMp;
    @FXML
    private Label label;
    @FXML
    private Button signup;
    @FXML
    private Pane pane;
    @FXML
    private   ImageView imageview2;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        imageview.fitWidthProperty().bind(borderpane.widthProperty());
        imageview.fitHeightProperty().bind(borderpane.heightProperty());
        imageview2.fitWidthProperty().bind(pane.widthProperty());
        imageview2.fitHeightProperty().bind(pane.heightProperty());
        vbox.prefWidthProperty().bind(borderpane.widthProperty());
        vbox.prefHeightProperty().bind(borderpane.heightProperty());

        email.prefWidthProperty().bind(vbox.widthProperty().subtract(100));
        mp.prefWidthProperty().bind(vbox.widthProperty().subtract(100));
        btnMp.prefWidthProperty().bind(vbox.widthProperty().subtract(100));
        login.prefWidthProperty().bind(vbox.widthProperty().subtract(100));
        line.endXProperty().bind(vbox.widthProperty().subtract(100));
        signup.prefWidthProperty().bind(borderpane.widthProperty());
label.prefHeightProperty().bind(borderpane.widthProperty());



    }
}
