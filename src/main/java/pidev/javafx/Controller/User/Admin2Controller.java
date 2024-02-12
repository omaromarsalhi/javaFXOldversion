package pidev.javafx.Controller.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.javafx.Model.user.Municipalite;
import pidev.javafx.Model.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class Admin2Controller implements Initializable {
    @FXML
    HBox munilayout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("helooo");
        List<Municipalite> muni = new ArrayList<>(municipalites());
        for (int i = 0; i < muni.size(); i++) {
            System.out.println(muni.get(i).getName());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/User/municiplaite_item.fxml"));
            try {
                HBox cardhBox = fxmlLoader.load();
                   municipaliteitemController muniitem = fxmlLoader.getController();
                muniitem.setData(muni.get(i));
              HBox.setMargin(cardhBox, new Insets(5, 10, 0, 0));
                munilayout.getChildren().add(cardhBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private List<Municipalite> municipalites (){
        List<Municipalite> ls=new ArrayList<>();
      Municipalite municipalite1=new Municipalite("Muncipalte de tunisie","ppppp");
      ls.add(municipalite1);
        Municipalite municipalite2=new Municipalite("tunisie","ppppp");
        ls.add(municipalite2);
        Municipalite municipalite3=new Municipalite("tunisie","ppppp");
        ls.add(municipalite2);
        Municipalite municipalite4=new Municipalite("tunisie","ppppp");
        ls.add(municipalite2);
        return ls;

    }
}
