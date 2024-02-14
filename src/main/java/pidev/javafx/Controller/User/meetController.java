package pidev.javafx.Controller.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class meetController implements Initializable {

    @FXML
    private DatePicker calndrier;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calndrier.setValue(LocalDate.now());
        calndrier.setShowWeekNumbers(true);
    }
}
