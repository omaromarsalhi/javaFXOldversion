package pidev.javafx.Controller.Chat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    @FXML
    private Button addImageBtn;
    @FXML
    private Button clearMsgBtn;
    @FXML
    private VBox itemDeatails;
    @FXML
    private Label messageLabel;
    @FXML
    private Button sendMsgBtn;
    @FXML
    private VBox chatContainer;



    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    @FXML
    public void onSendMsgBtnClicked(ActionEvent event){
        chatContainer.getChildren().add(createChatBox("omar salho ajkajk aaa"));
    }

    public VBox createChatBox(String text){
        VBox msgBox=new VBox();
        msgBox.setPrefSize( chatContainer.getPrefWidth(),chatContainer.getPrefHeight() );
        Label msgLabel=new Label();
        ImageView usernIcon=new ImageView( new Image("file:src/main/resources/img/me.png" ) );
        msgLabel.setText( text );
        msgBox.getChildren().addAll(msgLabel ,usernIcon );
        msgBox.setAlignment( Pos.CENTER );
        return msgBox;
    }


}
