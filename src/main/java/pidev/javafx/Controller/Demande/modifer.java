package pidev.javafx.Controller.Demande;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import pidev.javafx.Controller.Entity.Reclamation;
import pidev.javafx.Controller.Service.ServiceReclamation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class modifer {

    @FXML
    public ListView lista;
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
    ServiceReclamation service = new ServiceReclamation();
    List<Reclamation> reclamations = new ArrayList<>(service.getAll());

    // Add the Reclamation objects to the ListView
    lista.getItems().addAll(reclamations);

    // Optionally, you can set a custom cell factory to control how each Reclamation is displayed
    lista.setCellFactory(reclamationListView -> new ListCell<Reclamation>() {
        @Override
        protected void updateItem(Reclamation reclamation, boolean empty) {
            super.updateItem(reclamation, empty);
            if (empty || reclamation == null) {
                setText(null);
            } else {
                setText(reclamation.getPrivateKey() + " " + reclamation.getSubject() + " " + reclamation.getTitre());
            }
        }
    });
}

}

