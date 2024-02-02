package pidev.javafx.Controller.Transport;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import pidev.javafx.Controller.ConnectionDB;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class GareController {


    @FXML
   private TextArea Referance_text;



    @FXML
    private Button showBtn;

    @FXML
    private Button deleteBtn;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;



@FXML
    protected void onTextChanged() {
    String[] name = new String[10];

    name[1] = Referance_text.getText();

    if (name[1].matches("[a-zA-Z]+"))
        Referance_text.setStyle("-fx-control-inner-background: #25c12c;");
    else
        Referance_text.setStyle("-fx-control-inner-background: #bb2020;");}
//
//    if (  name[2].matches("[(a-zA-Z)|(1-9)]+"))
//        Name_area1.setStyle("-fx-control-inner-background: #25c12c;");
//    else
//        Name_area1.setStyle("-fx-control-inner-background: #bb2020;");
//
//
//    if (  name[3].matches("[a-zA-Z]+"))
//        Name_area2.setStyle("-fx-control-inner-background: #25c12c;");
//    else
//        Name_area2.setStyle("-fx-control-inner-background: #bb2020;");
//
//    if (  name[4].matches("[a-zA-Z]+"))
//        Name_area3.setStyle("-fx-control-inner-background: #25c12c;");
//    else
//        Name_area3.setStyle("-fx-control-inner-background: #bb2020;");
//
//
//    if (  name[5].matches("[a-zA-Z]+"))
//        Name_area4.setStyle("-fx-control-inner-background: #25c12c;");
//    else
//        Name_area4.setStyle("-fx-control-inner-background: #bb2020;");
//      }

@FXML
      public void afficher(){
    String sql = "INSERT INTO name "
                    + "(aa,bb) "
                    + "VALUES(?,?)";

            connect = ConnectionDB.connectDb();

            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1,  Referance_text.getText());
                prepare.setString(2,  Referance_text.getText());

                prepare.executeUpdate();

            } catch (Exception e) {
//            System.out.println("error");
                System.out.println(e.getMessage());
            }

      };

    @FXML
    public void supprimer(){
        String sql = "DELETE FROM name WHERE aa = ? ";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1,  Referance_text.getText());


            prepare.executeUpdate();
            System.out.println(  " row(s) deleted.");

        } catch (Exception e) {
//            System.out.println("error");
            System.out.println(e.getMessage());
        }

    };

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


