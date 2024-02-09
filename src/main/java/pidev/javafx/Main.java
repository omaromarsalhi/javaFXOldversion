package pidev.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidev.javafx.Controller.ConnectionDB;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
 //FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/mainWindow.fxml" ));
       FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/User/ListeUser.fxml" ));
   //FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource( "/fxml/User/signup .fxml" ));
        Scene scene = new Scene(fxmlLoader.load());
     //scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/styleAccount.css") ) );
     scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/StylelisteUsers.css") ) );
      //scene.getStylesheets().add( String.valueOf( getClass().getResource("/style/styleShowItems.css") ) );
      // scene.getStylesheets().add(String.valueOf(getClass().getResource("/style/styleAccount.css")));
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
        Connection connection = ConnectionDB.connectionDB();
        /*if (connection != null) {
            System.out.println("Connexion à la base de données réussie.");

            try {

                System.out.println("Connexion à la base de données fermée avec succès.");
            } catch (Exception e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }
*/
    }





    }
