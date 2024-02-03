package pidev.javafx.Controller.Transport;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev.javafx.Controller.ConnectionDB;
import pidev.javafx.Model.Transport.Transport;
import pidev.javafx.Model.Transport.Type_Vehicule;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class AddTransportController {

    @FXML
    private TextField ReferenceText;
    @FXML
    private TextField NumLigneText;
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

    Set<String> resultSetItems = new HashSet<>();



    //////Fonctions pour loader les parametrre du page insert
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
        text[2]= NumLigneText.getText();

        if (text[1].matches("[a-zA-Z0-9]*"))
            ReferenceText.setStyle("-fx-text-fill: #25c12c;");
        else
            ReferenceText.setStyle("-fx-text-fill: #bb2020;");

        if (text[2].matches("[0-9]+"))
            NumLigneText.setStyle("-fx-text-fill: #25c12c");
        else
            NumLigneText.setStyle("-fx-text-fill: #bb2020 ");
    }

public void insert_Image(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose a File");

    // Show the file dialog
    var selectedFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedFile != null) {
        // Set the selected file path on the button
        Ajouter_imageBtn.setText(selectedFile.getAbsolutePath());
imagePath=selectedFile.getAbsolutePath();
        System.out.println(imagePath);
    }

}
    @FXML
    protected boolean insertTransport(){
        int Reference = Integer.parseInt(ReferenceText.getText());
        int Num_Ligne=Integer.parseInt(NumLigneText.getText());
        String Type=BoxTypeVehicule.getValue().toString();
        String  DEPART=Depart.getValue().toString();
        String ARRIVEE=Arrive.getValue().toString();

        Transport T=new Transport(Reference,Type,Num_Ligne,DEPART,ARRIVEE,imagePath);
        String sql = "insert INTO transport(reference,Type_Vehicule,Num_Ligne,Depart,Arivee,Vehicule_Image) VALUES (?,?,?,?,?,?) ";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, T.getIdTransport());
            prepare.setString(2,T.getType_vehicule());
            prepare.setInt(3,T.getNum_ligne());
            prepare.setString(4,T.getDepart());
            prepare.setString(5,T.getArivee());
            prepare.setString(6,T.getVehicule_Image());
            prepare.executeUpdate();
            System.out.println(  " row(s) Inserted.");

        } catch (Exception e) {
//            System.out.println("error");
            System.out.println(e.getMessage());
        }
return true;

    }
}
