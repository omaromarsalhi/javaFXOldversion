package pidev.javafx.Controller.Transport;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.javafx.Controller.ConnectionDB;
import pidev.javafx.Services.ServicesTransport;
import pidev.javafx.entities.Transport.Transport;
import pidev.javafx.entities.Transport.Type_Vehicule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.sql.Time;
import java.time.LocalTime;
import pidev.javafx.Services.ServicesTransport;
import static javafx.stage.StageStyle.UNDECORATED;

public class AddTransportController implements Initializable {

    @FXML
    private TextField ReferenceText;
    @FXML
    private TextField PrixText;
    @FXML
    private ComboBox Depart;
    @FXML
    private ComboBox Arrive;
    @FXML
    private ComboBox BoxTypeVehicule;
    private Connection connect;

    private PreparedStatement prepare;
    @FXML
    private Button Add_photoBtn;
    @FXML
    private Button AnnulerBtn;
    @FXML
   private Button AddBtn;
    @FXML
   private Button Ajouter_imageBtn;
    String imagePath;
    private Stage primaryStage;

    @FXML
    private ScrollPane mainBorderPain;
    Set<String> resultSetItems = new HashSet<>();
    ActionEvent event;
    @FXML
    private Spinner<Integer> timeSpinner;
    @FXML
    private ImageView Image;
    ServicesTransport sp = new ServicesTransport();
    Dialog<String> dialog = new Dialog<>();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Transport/exceptions/Dialog.fxml"));
    Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    intialiase_timer();
    }



public  void intialiase_timer(){
    SpinnerValueFactory<Integer> timeFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(7 * 60 , 19 * 60 , 0,30);
    timeSpinner.setValueFactory(timeFactory);

     timeSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
        int hours = newValue / 60;
        int minutes = newValue % 60;
        timeSpinner.getEditor().setText(String.format("%02d:%02d", hours, minutes));
    });

     timeSpinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);

}



     public Set<String> Load_Locations(){

        String sql = "SELECT * FROM stations";
        connect = ConnectionDB.connectDb();

        try (
                PreparedStatement preparedStatement = connect.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {

                String name = resultSet.getString("NomStation");
                resultSetItems.add(name);

            }
            return resultSetItems;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @FXML
    protected  void     Load_Depart(){
        if(Depart.getValue()==null)
        Depart.getItems().addAll(Load_Locations());
    }
    @FXML
     protected  void Load_Arrivee(){
    if(Arrive.getValue()==null)
        Arrive.getItems().addAll(Load_Locations());
}
    @FXML
    protected  void Load_types() {
        if(BoxTypeVehicule.getValue()==null)
        BoxTypeVehicule.getItems().addAll(Type_Vehicule.values());
    }

    @FXML
    protected void onTextChanged() {
        String[] text = new String[10];

        text[1] = ReferenceText.getText();
        text[2]= PrixText.getText();

        if (text[1].matches("[a-zA-Z0-9]*"))
            ReferenceText.setStyle("-fx-text-fill: #25c12c;");
        else
            ReferenceText.setStyle("-fx-text-fill: #bb2020;");

        if (text[2].matches("[0-9 -.]+"))
            PrixText.setStyle("-fx-text-fill: #25c12c");
        else
            PrixText.setStyle("-fx-text-fill: #bb2020 ");
    }



    public void insert_Image(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose a File");
    var selectedFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedFile != null) {
imagePath=selectedFile.getAbsolutePath() ;
         Image image = new Image(imagePath);
        Image.setFitHeight(114);
        Image.setFitWidth(114);
        Image.setImage(image);

    }
}

    @FXML
    protected boolean insertTransport() throws IOException {

        String Type = BoxTypeVehicule.getValue().toString();
        String DEPART = Depart.getValue().toString();
        String ARRIVEE = Arrive.getValue().toString();
        Time Temp;
        int totalMinutes = timeSpinner.getValue();
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        LocalTime localTime = LocalTime.of(hours, minutes);

         Time time = Time.valueOf(localTime);

        if (ReferenceText.getText().matches("1") ||PrixText.getText() ==null  ) {


            System.out.println("Condition not met.");
            return false;
        }
        else {

                String Reference = ReferenceText.getText();
                Float Prix = Float.parseFloat(PrixText.getText());
                Transport T = new Transport( Type,  DEPART, ARRIVEE, Reference, imagePath, Prix,time);
                sp.ajouter(T);
            Pane successPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Transport/added_succesfully.fxml")));

            // Set content
            successPane.setPrefHeight(mainBorderPain.getPrefHeight());
            successPane.setPrefWidth(mainBorderPain.getPrefWidth());
            mainBorderPain.setContent(successPane);

            // Create PauseTransition for a 5-second delay
            PauseTransition pause = new PauseTransition(Duration.seconds(2.33));
            pause.setOnFinished(event -> {
                // Load another FXML after 5 seconds
                try {
                    Return(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Start the PauseTransition
            pause.play();

              //  showDialog();

            return true;
        }
    }



    @FXML
    public void showDialog() {
         try {
            AnchorPane dialogContent = loader.load();
            dialog.getDialogPane().setContent(dialogContent);
            dialog.setHeight(dialogContent.getHeight());
            dialog.setWidth(dialogContent.getWidth());

            stage.initStyle(UNDECORATED);
            dialog.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void closeDialog() {
        try {
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.close();
            System.out.println("Closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Return(ActionEvent event) throws IOException {
        ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/Display_Transport.fxml")));
        scrollPane.setPrefHeight(mainBorderPain.getPrefHeight()  );
        scrollPane.setPrefWidth( mainBorderPain.getPrefWidth() );
        mainBorderPain.setContent(scrollPane);
    }


}

