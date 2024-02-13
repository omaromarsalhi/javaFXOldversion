package pidev.javafx.Controller.Demande;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import pidev.javafx.Controller.Entity.Reclamation;
import pidev.javafx.Controller.Service.ServiceReclamation;

import java.util.Set;

public class modifer {

    @FXML
    public VBox box;
    ServiceReclamation si = new ServiceReclamation();
//    public void initialize() {
//        try {
//            // Get the data from the database
//
//            VBox box = new VBox();
//
//            // Iterate through the result set and create labels
//            for (Reclamation reclamation : si.getAllPrivateKeys()) {
//                String data = reclamation.getPrivateKey()+" "+ reclamation.getSubject() + " " + reclamation.getTitre(); // replace with your method names
//                Label label = new Label(data);
//                box.getChildren().add(label);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
public void initialize() {
    try {
        // Get the data from the database

        VBox box = new VBox();

        // Iterate through the result set and create labels
        for (String privateKey : si.getAllPrivateKeys()) {
            Label label = new Label(privateKey);
            box.getChildren().add(label);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

