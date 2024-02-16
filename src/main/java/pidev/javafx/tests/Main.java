package pidev.javafx.tests;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pidev.javafx.Controller.Connection.DataSource;
import pidev.javafx.Controller.Entity.Reclamation;

import java.io.IOException;
<<<<<<< HEAD:src/main/java/pidev/javafx/Main.java
import java.sql.Connection;
=======
import javafx.stage.StageStyle;
>>>>>>> Aziz_Gmaty_branch:src/main/java/pidev/javafx/tests/Main.java

public class Main extends Application {


    public void start(Stage stage) throws IOException {
<<<<<<< HEAD:src/main/java/pidev/javafx/Main.java
        FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/mainWindow.fxml" ));
<<<<<<< HEAD
        //       FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/marketPlace/myMarket.fxml" ));
=======
//        FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/marketPlace/myMarket.fxml" ));
>>>>>>> 2bfe4276af099b37f7920e215c3cd04636278f5f
=======
      FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/mainWindow.fxml" ));
//        FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/marketPlace/myMarket.fxml" ));
>>>>>>> Aziz_Gmaty_branch:src/main/java/pidev/javafx/tests/Main.java
//        FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/Employe/showEmployee.fxml" ));
        Scene scene = new Scene(fxmlLoader.load());
//        scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/Buttons.css") ) );
//        scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/styleShowItems.css") ) );

        stage.setTitle("Hello!");
        stage.setResizable( true );
//        stage.setWidth( 950 );
//        stage.setHeight(600);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> System.exit(0));
    }


    public static void main(String[] args) {
        launch();
    }
}