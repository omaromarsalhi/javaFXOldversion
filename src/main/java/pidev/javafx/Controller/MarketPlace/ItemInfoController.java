package pidev.javafx.Controller.MarketPlace;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pidev.javafx.Model.MarketPlace.Bien;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemInfoController {

    @FXML
    private ImageView exitInfo;

    @FXML
    private VBox itemDeatails;

    @FXML
    private TextArea itemDesc;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label userFirstName;

    @FXML
    private ImageView userImage;

    @FXML
    private Label userLastName;



    private MyListener myListener;
    private Bien bien;

    public void setData(Bien bien,MyListener myListener) {
        this.bien=bien;
        this.myListener=myListener;
        userFirstName.setText("Omar");
        userLastName.setText("Salhi");
        itemImage.setImage(new Image(getClass().getResourceAsStream( bien.getImgSource())));
        itemDesc.setText( "qsfdgoauiehrtgpbea ufhgae ouifehg dfvb ae rhtgqfvhbj aert qfvhuaerçtg" );
    }

    @FXML
    public void onExitBtnClicked(ActionEvent event){
       myListener.exit();
    }
}
