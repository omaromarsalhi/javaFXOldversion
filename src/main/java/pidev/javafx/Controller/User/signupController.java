package pidev.javafx.Controller.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pidev.javafx.utlis.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.Connection;


public class signupController {
    @FXML
    private Button btnsignup;
    private Connection connect;
    private PreparedStatement prepare;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;





   /* public void btnSignup(ActionEvent actionEvent) {
        String query = "INSERT INTO user (firstName,lastname,email,password) VALUES(?,?,?,?)";
//
          connect = ConnectionDB.connectionDB();
//
          try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1,firstname.getText() );
            prepare.setString(2, lastname.getText());
            prepare.setString(3, email.getText());
            prepare.setString(4, password.getText());

//                prepare.setString(7, String.valueOf(sDate.getValue()));
//                prepare.setString(8, cin.getText());
            prepare.executeUpdate();
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/User/login.fxml"));
              Scene scene = new Scene(fxmlLoader.load());

//
         } catch (Exception e) {
         System.out.println("error");
               System.out.println(e.getMessage());}
    }*/

    }

