package pidev.javafx.Controller.Demande;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class modifer {
    @FXML
    private ListView<String> listView; // Assuming you have a ListView in your FXML with the id "list"

    public void initialize() {
        // Initialize your database connection (e.g., using JDBC)
        // Fetch data from the database (e.g., SELECT query)
        ObservableList<String> items = FXCollections.observableArrayList();
        // Add data to the observable list
        items.add("Item 1");
        items.add("Item 2");
        // Set the ListView to display the data
        listView.setItems(items);
    }
}
