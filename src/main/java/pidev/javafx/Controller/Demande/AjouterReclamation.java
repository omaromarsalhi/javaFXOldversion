package pidev.javafx.Controller.Demande;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import pidev.javafx.Controller.Entity.Reclamation;
import pidev.javafx.Controller.Service.Iservice;
import pidev.javafx.Controller.Service.ServiceReclamation;

import java.io.File;
import java.security.PrivateKey;
import java.util.Random;

public class AjouterReclamation {

    @FXML
    private Button importButton;

    @FXML
    private TextField privateKey;
    @FXML
    private TextField title;
    @FXML
    private TextField subject;

    @FXML
    private TextArea description ;

    ServiceReclamation si = new ServiceReclamation();



    public void importFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            System.out.println("File imported");
        } else {
            System.out.println("File selection cancelled.");
        }
    }
    @FXML
    void ajouter_Reclamation()
    {
        Reclamation   rec = new Reclamation(privateKey.getText(), title.getText(),subject.getText() ,description.getText());
        si.ajouter(rec);
    }
}
