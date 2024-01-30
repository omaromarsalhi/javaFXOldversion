package pidev.javafx.Controller.MarketPlace;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;





public class FormController {

    @FXML
    private TextField Pname;


    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    public void onNameSelected(){
        Pname.setStyle( "-fx-background-color: red;"+
                " -fx-border-color: black grey grey transparent;" +
                " -fx-start-margin: 100px;"+
                "    -fx-border-width: 1px;" +
                "    -fx-border-radius: 0 10px 10px 0;"+
                "    -fx-background-radius: 0 10px 10px 0;");
    }

    @FXML
    public void onAddBienClicked(){
//            String sql = "INSERT INTO bien "
//                    + "(firstName,lastName,address,dateOfBirth,password,phNumber,startDate,cin) "
//                    + "VALUES(?,?,?,?,?,?,?,?)";
//
//            connect = Connection2DB.connectDb();
//
//            try {
//                prepare = connect.prepareStatement(sql);
//                prepare.setString(1, fName.getText());
//                prepare.setString(2, lName.getText());
//                prepare.setString(3, address.getText());
//                prepare.setString(4, String.valueOf(dob.getValue()));
//                prepare.setString(5, password.getText());
//                prepare.setString(6, phNumber.getText());
//                prepare.setString(7, String.valueOf(sDate.getValue()));
//                prepare.setString(8, cin.getText());
//                prepare.executeUpdate();
//
//            } catch (Exception e) {
////            System.out.println("error");
//                System.out.println(e.getMessage());
//            }
    }
}
