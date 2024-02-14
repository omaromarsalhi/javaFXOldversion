package pidev.javafx.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
 //FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/mainWindow.fxml" ));
       //FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/User/Account.fxml" ));
   //FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/User/signup .fxml" ));
        //FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/User/meet.fxml" ));
      FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/User/ListeUser.fxml" ));
       // FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/User/LoginSignup.fxml" ));
       //FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/User/Admin2.fxml" ));
        Scene scene = new Scene(fxmlLoader.load());
        //scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/styleAdmin2.css") ) );
     // scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/styleLogin.css") ) );
     scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/StylelisteUsers.css") ) );
      //scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/styleShowItems.css") ) );
    // scene.getStylesheets().add(String.valueOf(getClass().getResource("/style/styleMeet.css")));
        // stage.setTitle("Hello!");
        stage.setResizable( true );
    // stage.setWidth( 200 );
//     stage.setHeight(500);
    stage.setScene(scene);
       stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setOnCloseRequest(event -> javafx.application.Platform.exit());
    }


    public static void main(String[] args) {
       launch();




    }





    }
