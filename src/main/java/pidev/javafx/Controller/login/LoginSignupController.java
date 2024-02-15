package pidev.javafx.Controller.login;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import pidev.javafx.Controller.User.AccountController;
import pidev.javafx.Controller.User.ServiceUser;
import pidev.javafx.Model.user.Role;
import pidev.javafx.Model.user.User;
import pidev.javafx.test.Main;


import java.io.IOException;
import java.net.URL;
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
    @FXML
    private Button alertename;

    @FXML
    private Button alerteemail;

    @FXML
    private Button alerteadress;

    @FXML
    private Button alertepassword;







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
        alertename.setVisible(false);
        alerteadress.setVisible(false);
        alerteemail.setVisible(false);
        alertepassword.setVisible(false);

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
        alerteemail.setVisible(false);
        alertename.setVisible(false);
        alertepassword.setVisible(false);

        String nameRegex = "^[a-zA-Z]+$";
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        User user = new User();
        if (name.getText() != "" && email.getText() != "" && password.getText() != "") {


            if (name.getText().matches(nameRegex) && email.getText().matches(emailRegex) && password.getText().matches(passwordRegex)) {
                 ServiceUser service = new ServiceUser();
                  User usexist = new User();
                  usexist = service.findParEmail(email.getText());

                if (usexist == null) {

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
            else{

                if(!name.getText().matches(nameRegex)){
                    alertename.setVisible(true);
                    alertename.setOnMouseClicked(event -> {


                        Alert alert;
                        alert=showAlert("ddddddd","le nom ne contient que des lettres de l'alphabet");
                        Region root = (Region) alert.getDialogPane().getChildren().get(0);
                        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), root);
                        fadeTransition.setFromValue(0.0); // Opacité initiale
                        fadeTransition.setToValue(1.0); // Opacité finale


                        alert.show();
                        fadeTransition.play();


                    });
                }

                if(!email.getText().matches(emailRegex)){
                    alerteemail.setVisible(true);
                    alerteemail.setOnMouseClicked(event -> {


                        Alert alert;
                        alert=showAlert("ddddddd","L'e-mail doit être sous la forme _@.");
                        Region root = (Region) alert.getDialogPane().getChildren().get(0);
                        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), root);
                        fadeTransition.setFromValue(0.0); // Opacité initiale
                        fadeTransition.setToValue(1.0); // Opacité finale


                        alert.show();
                        fadeTransition.play();


                    });
                }

                if(!password.getText().matches(passwordRegex)){

                    alertepassword.setVisible(true);
                    alertepassword.setOnMouseClicked(event -> {


                        Alert alert;
                        alert=showAlert("ddddddd","Il doit contenir au moins un chiffre.\n" +
                                ",au moins une lettre minuscule.\n" +
                                ",au moins une lettre majuscule.\n" +
                                "Il doit avoir une longueur minimale de 8 caractères."+
                                ",au moins un caractère spécial parmi [@#$%^&+=].\n" +
                                "Il ne doit pas contenir d'espaces blancs.\n"
                                );
                        Region root = (Region) alert.getDialogPane().getChildren().get(0);
                        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), root);
                        fadeTransition.setFromValue(0.0); // Opacité initiale
                        fadeTransition.setToValue(1.0); // Opacité finale


                        alert.show();
                        fadeTransition.play();


                    });
                }


            }
    }

    }



    public static Alert showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setPrefWidth(290);
        alert.getDialogPane().setPrefHeight(200);
        alert.getDialogPane().getStylesheets().add(Main.class.getResource("/style/styleSignup.css").toExternalForm());
       return alert;
    }







    public void Authentifier(ActionEvent actionEvent) {

        User user = new User();
        user.setEmail(username.getText());
        user.setPassword(password.getText());

        ServiceUser service=new ServiceUser();


            String pass=service.RetriveHashedPassword(user.getEmail());
            System.out.println(pass);
            if(pass==null){
//donc usermafamech
            }
            else
            {
                if(PasswordHasher.verifyPassword(user.getPassword(),pass)){

                    user=service.findParEmail(user.getEmail());
                    user.setIsConnected(1);
                    System.out.println(user.getIsConnected());
                   service.isconnected(user);
                    if(user.getRole()==Role.simpleutlisateur) {
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
                    if(user.getRole()==Role.responsable){
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/User/ListeUser.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                        scene.getStylesheets().add(String.valueOf(getClass().getResource("/style/StylelisteUsers.css")));
                        Stage stage;
                        stage = (Stage) signup2.getScene().getWindow();
                        stage.setScene(scene);
                        stage.close();
                        stage.show();
                    }

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
