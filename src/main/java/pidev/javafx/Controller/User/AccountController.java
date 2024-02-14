package pidev.javafx.Controller.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pidev.javafx.Model.user.User;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    private boolean isInitializeAllowed = false;
    @FXML
    VBox Vbox1;
    @FXML
    Button btn1;
    @FXML
    Pane panee;

    @FXML
    Button btnR;
    @FXML
    Button dashboard;
    @FXML
    TextField firstname;
    @FXML
    TextField email;
    @FXML
    private DatePicker dob;

    @FXML
    private TextField status;
    @FXML
    private TextField lastname;

    @FXML
    private TextField cin;

    @FXML
    private TextField age;

    @FXML
    private TextField adresse;

    @FXML
    private TextField telephone;
    @FXML
    private Label labelname;

    @FXML
    private Label labelemail;
    @FXML
    private ImageView isconnected;


   private FXMLLoader fxmlLoader;

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }


    public AccountController() {
    }
    public void setFXMLLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }





    @FXML
    public void onb1bouttonclick(ActionEvent event) {
        Vbox1.setVisible(!Vbox1.isVisible());
        Vbox1.setPrefHeight(-100);
        Vbox1.setPrefWidth(10);
        panee.setPrefWidth(350);
        //panee.setPrefHeight();

    }
@FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void display(User user)
    {
        this.firstname.setText(user.getFirstname());
        this.lastname.setText(user.getLastname());
        this.email.setText(user.getEmail());
        this.adresse.setText(user.getAdresse());
        this.cin.setText(user.getCin());
        this.status.setText(user.getStatus());
        this.age.setText(String.valueOf(user.getAge()));
        this.telephone.setText(String.valueOf(user.getNum()));
        //this.dob.setValue(LocalDate.parse(user.getDob()));
        this.labelemail.setText((user.getEmail()));
        this.labelname.setText(user.getFirstname()+" "+user.getLastname());
        this.isconnected.setVisible(true);
    }

    public void btnModifier(ActionEvent actionEvent) {

          User user=new User();
          user.setFirstname(firstname.getText());
          user.setEmail(email.getText());
          user.setEmail(lastname.getText());
          user.setAge(Integer.parseInt(age.getText()));
          user.setCin(cin.getText());
          user.setDob(String.valueOf(dob.getValue()));
          user.setNum(Integer.parseInt(telephone.getText()));
          user.setStatus(status.getText());
          ServiceUser serviceUser = new ServiceUser();
          serviceUser.modifier(user);


    }

    public void onbtn1(ActionEvent actionEvent) {
        ServiceUser serviceUser = new ServiceUser();
        serviceUser.supprimer(Integer.parseInt(cin.getText()));
    }
}
