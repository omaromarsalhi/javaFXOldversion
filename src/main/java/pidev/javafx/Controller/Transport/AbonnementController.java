package pidev.javafx.Controller.Transport;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pidev.javafx.Controller.ConnectionDB;
import pidev.javafx.Services.ServicesAbonnement;
import pidev.javafx.entities.Transport.Abonnement;
import pidev.javafx.entities.Transport.Transport;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class AbonnementController implements Initializable {

    private Connection connect;

    ObservableList<Transport> dataList = FXCollections.observableArrayList();
    List<Abonnement> abonnementList = new ArrayList<>();
    ServicesAbonnement sa =new ServicesAbonnement();
    @FXML
    Pane paneToAnnimate;



    @FXML
    private Label DebutLabel;

    @FXML
    private Label FinLabel;

    @FXML
    private Label IdLabel;

    @FXML
    private Label NomLabel;

    @FXML
    private Label PrenomLabel;
    @FXML
            private Button nextBtn;
    @FXML
            private Button previousBtn;
       int i;
       Set <Abonnement> abonnementSet;
    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), paneToAnnimate);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // toolsBar.setVisible(false);
        afficher();
        remplir_abonnement();
        translateTransition.setNode(paneToAnnimate);

    }

    @FXML
    public void afficher() {


            abonnementSet=sa.getAll();
        abonnementList = List.copyOf(abonnementSet);

            System.out.println(abonnementList.toString());
//            System.out.println(abonnementList.get(1).toString());
//            System.out.println(abonnementList.get(2).toString());


    }


    public  void remplir_abonnement(){
        if (i==0)
            previousBtn.setVisible(false);
        else if (i==abonnementList.size()-1) {
            nextBtn.setVisible(false);
        }
String [] time=abonnementList.get(i).getDateDebut().toLocalDateTime().toString().split("T");
String id=Integer.toString(abonnementList.get(i).getIdAboonnement());
         DebutLabel.setText(time[0]);
        FinLabel.setText(abonnementList.get(i).getDateFin().toString());
        NomLabel.setText(abonnementList.get(i).getNom());
        PrenomLabel.setText(abonnementList.get(i).getPrenom());
        IdLabel.setText("000"+id);

    }
    @FXML
    public void nextAb() {
        translateTransition.setToX(400);
        translateTransition.play();
        if (i < abonnementList.size() - 1) {
            nextBtn.setVisible(true);
            previousBtn.setVisible(true);
            i = i + 1;
            System.out.println(i);
            remplir_abonnement();
        } else {
            nextBtn.setVisible(false);
            previousBtn.setVisible(true);
        }
    }

    @FXML
    public void previousAb() {
        translateTransition.setToX(-400);
        translateTransition.play();
        if (i > 0) {
            previousBtn.setVisible(true);
            nextBtn.setVisible(true);
            i = i - 1;
            System.out.println(i);
            remplir_abonnement();
        } else if (i == 0) {
            previousBtn.setVisible(false);
            nextBtn.setVisible(true);
        }
    }
@FXML
    VBox statsPannel;
    @FXML
    VBox toolsBar;
    @FXML
    Pane statsPane;
    @FXML
    Button expandBtn;

    @FXML
    public void expand(){
//        ScaleTransition st = new ScaleTransition(Duration.seconds(1), statsPannel);
//        st.setFromX(1); // Initial horizontal scale factor
//        st.setToX(0.5); // Final horizontal scale factor
//        //st.setFromY(1); // Initial vertical scale factor
//       // st.setToY(1); // Final vertical scale factor
//        //st.setAutoReverse(true); // Reverse the animation when it reaches the end
//        //st.setCycleCount(2); // Repeat the animation twice
//
//        // Start the animation when the stage is shown
//         st.play();


        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);

        // Create the initial and final values for the width and the translateX properties of the VBox
        KeyValue initWidth = new KeyValue(statsPannel.prefWidthProperty(), 260);
        KeyValue initTranslateX = new KeyValue(statsPannel.translateXProperty(), 50);
        KeyValue finalWidth = new KeyValue(statsPannel.prefWidthProperty(), 50);
        KeyValue finalTranslateX = new KeyValue(statsPannel.translateXProperty(), 260);


        // Create a KeyFrame with the initial values and a duration of zero
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initWidth, initTranslateX);

        // Create a KeyFrame with the final values and a duration of one second
        KeyFrame finalFrame = new KeyFrame(Duration.seconds(1), finalWidth, finalTranslateX);

        // Add the KeyFrames to the Timeline
        timeline.getKeyFrames().addAll(initFrame, finalFrame);
        //toolsBar.setVisible(true);
        statsPane.setVisible(false);
        expandBtn.setVisible(true);

        // Start the animation when the stage is shown
         timeline.play();
    }


    @FXML
    public void unexpand(){
        // Create another Timeline animation to open the VBox
        Timeline timeline2 = new Timeline();
        timeline2.setCycleCount(1);
        timeline2.setAutoReverse(false);

// Create the initial and final values for the width and the translateX properties of the VBox
        KeyValue initWidth2 = new KeyValue(statsPannel.prefWidthProperty(), 50);
        KeyValue initTranslateX2 = new KeyValue(statsPannel.translateXProperty(), 260);
        KeyValue finalWidth2 = new KeyValue(statsPannel.prefWidthProperty(), 260);
        KeyValue finalTranslateX2 = new KeyValue(statsPannel.translateXProperty(), 50);

// Create a KeyFrame with the initial values and a duration of zero
        KeyFrame initFrame2 = new KeyFrame(Duration.ZERO, initWidth2, initTranslateX2);

// Create a KeyFrame with the final values and a duration of one second
        KeyFrame finalFrame2 = new KeyFrame(Duration.seconds(1), finalWidth2, finalTranslateX2);

// Add the KeyFrames to the Timeline
        timeline2.getKeyFrames().addAll(initFrame2, finalFrame2);


            // Set the visibility of the toolsBar, the statsPane, and the expandBtn to the opposite of what they were before
           // toolsBar.setVisible(false);
            statsPane.setVisible(true);
            expandBtn.setVisible(false);

            // Play the second animation
            timeline2.play();
        ;

    }

}
