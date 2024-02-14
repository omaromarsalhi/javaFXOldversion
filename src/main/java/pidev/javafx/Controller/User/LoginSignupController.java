package pidev.javafx.Controller.User;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import pidev.javafx.Model.user.Role;
import pidev.javafx.Model.user.User;
import pidev.javafx.test.Main;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginSignupController implements Initializable {
    @FXML
    private AnchorPane layer2;
    @FXML
    private AnchorPane layer1;

    @FXML
    private Label welcome;

    @FXML
    private Label hello;

    @FXML
    private Button signin;

    @FXML
    private Button signup;

    @FXML
    private TextField username;



    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Label forget;

    @FXML
    private Button signin2;

    @FXML
    private Button signup2;
    @FXML
    private TextField adresse;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome.setVisible(true);
        hello.setVisible(false);
        signin.setVisible(true);
        signup.setVisible(false);
        username.setVisible(false);
        password.setVisible(true);
        forget.setVisible(false);
        signin2.setVisible(false);
        email.setVisible(true);
        name.setVisible(true);

        signup2.setVisible(true);

    }




    @FXML
    void btn(MouseEvent event) {
        TranslateTransition slide =new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);
        slide.setToX(470);
        slide.play();
        layer1.setTranslateX(-233);
        welcome.setVisible(false);
        hello.setVisible(true);
        signin.setVisible(false);
        signup.setVisible(true);
        username.setVisible(true);
        password.setVisible(true);
        forget.setVisible(true);
        signin2.setVisible(true);
        email.setVisible(false);
        name.setVisible(false);
        signup2.setVisible(false);
        adresse.setVisible(false);

        slide.setOnFinished((e->{


        }));


    }



    @FXML
    void btn2(MouseEvent event) {
        TranslateTransition slide =new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));

        slide.setNode(layer2);
        slide.setToX(0);
        slide.play();
        layer1.setTranslateX(0);
        welcome.setVisible(true);
        hello.setVisible(false);
        signin.setVisible(true);
        signup.setVisible(false);
        username.setVisible(false);
        password.setVisible(true);
        forget.setVisible(false);
        signin2.setVisible(false);
        email.setVisible(true);
        name.setVisible(true);
        signup2.setVisible(true);
        adresse.setVisible(true);

        slide.setOnFinished((e->{


        }));

    }



    @FXML
    public void CreateAccount(ActionEvent actionEvent) throws IOException {

    User user = new User();
        if (name.getText() != "" && email.getText()!="" && password.getText()!=""){
            ServiceUser service=new ServiceUser();
            User usexist= new User();
            usexist=service.findParEmail(email.getText());

                 if(usexist==null) {

                     user.setFirstname(name.getText());
                     user.setEmail(email.getText());
                     user.setAdresse(adresse.getText());

                     user.setPassword(PasswordHasher.hashPassword(password.getText()));
                     System.out.println(user.getPassword());
                     user.setRole(Role.simpleutlisateur);

                     ServiceUser serviceUser = new ServiceUser();
                     serviceUser.ajouterUser(user);

                     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/User/Account.fxml"));
                     Scene scene = new Scene(fxmlLoader.load());

                     AccountController account = fxmlLoader.getController();
                     account.display(user);
                     scene.getStylesheets().add(String.valueOf(getClass().getResource("/style/styleAccount.css")));
                     Stage stage;
                     stage = (Stage) signup2.getScene().getWindow();
                     stage.setScene(scene);
                     stage.close();
                     stage.show();

                 }
        }

    }



    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(Main.class.getResource("/style/styleSignup.css").toExternalForm());
        alert.showAndWait();
    }


    public void Authentifier(ActionEvent actionEvent) {

        User user = new User();
        user.setEmail(username.getText());
        user.setPassword(password.getText());

        ServiceUser service=new ServiceUser();


            String pass=service.RetriveHashedPassword(user.getEmail());
        System.out.println(pass);
            if(pass==null){

            }
            else
            {
                if(PasswordHasher.verifyPassword(user.getPassword(),pass)){

                    user=service.findParEmail(user.getEmail());
                    System.out.println(user.getCin());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/User/Account.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    AccountController account = fxmlLoader.getController();
                    account.display(user);
                    scene.getStylesheets().add(String.valueOf(getClass().getResource("/style/styleAccount.css")));
                    Stage stage;
                    stage = (Stage) signup2.getScene().getWindow();
                    stage.setScene(scene);
                    stage.close();
                    stage.show();

                }
                else{

                    System.out.println("ce utlistateur n'existe pas ");
                    showAlert("utlisateur n'existe pas ","il faut s'inscrire");
                    username.clear();
                    password.clear();

                }
            }






    }




}
