package pidev.javafx.Controller.Demande;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import pidev.javafx.Controller.Entity.Reclamation;
import pidev.javafx.Controller.Service.Iservice;
import pidev.javafx.Controller.Service.ServiceReclamation;

import java.io.File;
import java.net.URL;
import java.security.PrivateKey;
import java.util.Random;
import java.util.ResourceBundle;

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
   @FXML
    public ChoiceBox ChoixMul;
    @FXML
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
        initialize();
        Reclamation   rec = new Reclamation(privateKey.getText(), title.getText(),subject.getText() ,description.getText());
        si.ajouter(rec);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Reclmation has been added successfully!");

        // Show the alert
        alert.show();
    }
    public void initialize() {
        ChoixMul.getItems().removeAll(ChoixMul.getItems());
        ChoixMul.getItems().addAll("Problem technique", "application", "testt","omar salhi","khalil rmila ");
        ChoixMul.getSelectionModel().select("khalil rmila");
    }

//    void ajouter_Reclamation()
//    {
//        String selectedSubject = initialize();
//        if (selectedSubject == null) {
//            // Handle the case where no item is selected.
//            // For example, you could show an error message to the user.
//        } else {
//            Reclamation rec = new Reclamation(privateKey.getText(), title.getText(), selectedSubject, description.getText());
//            si.ajouter(rec);
//
//        }
//    }
//
//
//
//    public String initialize() {
//        ChoixMul.getItems().removeAll(ChoixMul.getItems());
//        ChoixMul.getItems().addAll("Problem technique", "application", "testt","omar salhi","khalil rmila ");
//        return (String) ChoixMul.getSelectionModel().getSelectedItem();
//    }

}
