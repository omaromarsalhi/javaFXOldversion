package pidev.javafx.Controller.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

public class AccountController {
    @FXML
    VBox Vbox1;
    @FXML
    Button btn1;
    @FXML
    Pane panee;

    @FXML
    Button btnR;
    @FXML
    Button dashboard;
    @FXML
    public void onb1bouttonclick(ActionEvent event) {
        Vbox1.setVisible(!Vbox1.isVisible());
        Vbox1.setPrefHeight(-100);
        Vbox1.setPrefWidth(10);
        panee.setPrefWidth(350);
        //panee.setPrefHeight();

    }
}
