package pidev.javafx.Controller.Demande;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

public class AjouterReclamation {
    @FXML
    private Button importButton;
    public void importFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            System.out.println("File imported");
        } else {
            System.out.println("File selection cancelled.");
        }
    }
}
