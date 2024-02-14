package pidev.javafx.Controller.Chat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.layout.HBox.setMargin;

public class ChatController implements Initializable {

    @FXML
    private Button addImageBtn;
    @FXML
    private Button clearMsgBtn;
    @FXML
    private VBox itemDeatails;
    @FXML
    private TextField messageTextField;
    @FXML
    private Button sendMsgBtn;
    @FXML
    private VBox chatContainer;


    private List<File>  chosenFiles;



    @Override
    public void initialize(URL location, ResourceBundle resources){
        chosenFiles=null;
    }



    public HBox createTextChatBox(String text){
        HBox msgBox=new HBox();
        msgBox.setPrefWidth( chatContainer.getPrefWidth());
//        msgBox.setStyle( "-fx-background-color: red" );
        msgBox.setAlignment( Pos.BOTTOM_RIGHT );
        msgBox.setPadding( new Insets( 0,0,0,10 ) );
        msgBox.setSpacing( 4 );


        Label msgLabel=new Label();
//        msgLabel.setStyle( "-fx-background-color: blue" );
        msgLabel.setStyle( "-fx-background-color:  #D9D9D9;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;");
        msgLabel.setMinHeight( 40 );
        msgLabel.setText(text);
        msgLabel.setMinHeight( Region.USE_PREF_SIZE);
        msgLabel.setWrapText( true );
        msgLabel.setPadding( new Insets( 6 ) );

        Label timeLabel=new Label();
        timeLabel.setStyle( "-fx-font-size: 10;");
        timeLabel.setMinSize( 25,15 );

        timeLabel.setText( LocalTime.now().format( DateTimeFormatter.ofPattern("hh:mm")) );

        ImageView usernIcon=new ImageView( new Image("file:src/main/resources/img/me.png",16,16,false,false) );
        setMargin(usernIcon,new Insets( 0,0,4,10 ));

        msgBox.getChildren().addAll(msgLabel,timeLabel ,usernIcon );
        return msgBox;
    }

    public HBox createImageChatBox(String path){
        HBox msgBox=new HBox();
        msgBox.setPrefWidth( chatContainer.getPrefWidth());
//        msgBox.setStyle( "-fx-background-color: red" );
        msgBox.setAlignment( Pos.BOTTOM_RIGHT );
        msgBox.setPadding( new Insets( 0,0,0,10 ) );
        msgBox.setSpacing( 4 );


        ImageView image=new ImageView( new Image(path,80,100,true,true) );

//        msgLabel.setStyle( "-fx-background-color: blue" );
        image.setStyle( "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;");

        Label timeLabel=new Label();
        timeLabel.setStyle( "-fx-font-size: 10;");
        timeLabel.setMinSize( 25,15 );

        timeLabel.setText( LocalTime.now().format( DateTimeFormatter.ofPattern("hh:mm")) );

        ImageView usernIcon=new ImageView( new Image("file:src/main/resources/img/me.png",16,16,false,false) );
        setMargin(usernIcon,new Insets( 0,0,4,10 ));

        msgBox.getChildren().addAll(image,timeLabel ,usernIcon );
        return msgBox;
    }

    @FXML
    public void onSendMsgBtnClicked(){

        if(chosenFiles==null)
            chatContainer.getChildren().add(createTextChatBox(messageTextField.getText()));
        else {
            for (int i = 0; i < chosenFiles.size(); i++)
                chatContainer.getChildren().add( createImageChatBox( chosenFiles.get( i ).getAbsolutePath() ) );
            chosenFiles=null;
        }

        messageTextField.clear();
    }

    @FXML
    public void chooseImage(){
            FileChooser fileChooser = new FileChooser();
            setExtFilters(fileChooser);
            fileChooser.setTitle("chose Image");
            chosenFiles  = fileChooser.showOpenMultipleDialog( Stage.getWindows().get(0) );
            String val="";
        for(int i=0;i<chosenFiles.size();i++)
            val+="Img "+(i+1)+" ";
        messageTextField.setText(val );

    }

    private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

}
