package pidev.javafx.Controller.User;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



import java.net.URL;


import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import pidev.javafx.Main;
import pidev.javafx.Model.user.user;

public class listUserController implements Initializable {
    @FXML
    private VBox Userlayout;
    @FXML
    private TextField bar_recherche;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
List<user> user=new ArrayList<>(users());
for(int i=0;i< user.size();i++)
{
    FXMLLoader fxmlLoader=new FXMLLoader();
     fxmlLoader.setLocation(getClass().getResource( "/fxml/User/user_item.fxml" ));
    try {
        HBox hBox=fxmlLoader.load();
        UseritemController useritem =fxmlLoader.getController();
        useritem.setData(user.get(i));
        HBox.setMargin(hBox,new Insets(70,0,0,0));
        Userlayout.getChildren().add(hBox);



    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}


    }
    private void fadeIn(TextField node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        node.setVisible(true);
    }

    private void fadeOut(TextField node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), node);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event -> node.setVisible(false));
        fadeTransition.play();
    }
    private List<user> users(){
List<user> ls=new ArrayList<>();
user user1=new user("omar","benzaied","latifabenzaied@esprit.tn");
ls.add(user1);
        user user2=new user("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        user user3=new user("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        user user4=new user("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        user user5=new user("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        user user6=new user("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        user user7=new user("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        user user8=new user("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        user user9=new user("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        return ls;
    }

@FXML
    public void onclick(ActionEvent actionEvent) {
    showDetails();
    }

    private void showDetails() {


        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/User/Details.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getRoot().setStyle("-fx-padding: 5px;");
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/style/styleDetails.css")));
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();

            Stage dialogStage = new Stage();
            dialogStage.setScene(scene);
            dialogStage.setTitle("Détails");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initStyle(StageStyle.UNDECORATED);
            DetailsController detailsController = loader.getController();
            detailsController.setScene(scene);
            dialogStage.showAndWait();
        } catch (Exception e) {

            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'affichage de la boîte de dialogue.");
            alert.showAndWait();
        }
    }
}

