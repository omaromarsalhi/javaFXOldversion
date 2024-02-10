package pidev.javafx.Controller.User;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



import java.net.URL;


import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import pidev.javafx.Model.user.User;

public class listUserController implements Initializable {
    @FXML
    private VBox Userlayout;
    @FXML
    private TextField bar_recherche;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceUser service=new ServiceUser();
        Set<User> users = new HashSet<>(service.getAll());
       // List<User> user=new ArrayList<>(users());
//for(int i=0;i< user.size();i++)
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext())
{
    FXMLLoader fxmlLoader=new FXMLLoader();
     fxmlLoader.setLocation(getClass().getResource( "/fxml/User/user_item.fxml" ));
    try {
        HBox hBox=fxmlLoader.load();
        UseritemController useritem =fxmlLoader.getController();
        useritem.setData(iterator.next());
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
    private List<User> users(){
List<User> ls=new ArrayList<>();
User user1=new User("omar","benzaied","latifabenzaied@esprit.tn");
ls.add(user1);
        User user2=new User("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        User user3=new User("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        User user4=new User("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        User user5=new User("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        User user6=new User("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        User user7=new User("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        User user8=new User("latifa","benzaied","latifabenzaied@esprit.tn");
        ls.add(user2);
        User user9=new User("latifa","benzaied","latifabenzaied@esprit.tn");
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

