package pidev.javafx.Controller.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.javafx.Model.user.Municipalite;
import pidev.javafx.Model.user.User;

public class municipaliteitemController {
    @FXML
    public HBox hbox;

    @FXML
    private ImageView image;

    @FXML
    private VBox vbox;

    @FXML
    private Label label1;

    @FXML
    private Label label2;
    public void setData(Municipalite municipalite){
        label1.setText(municipalite.getName());
        label2.setText(municipalite.getAdresse());

    }
}
