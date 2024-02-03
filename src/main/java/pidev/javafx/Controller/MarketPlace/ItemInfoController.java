package pidev.javafx.Controller.MarketPlace;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Label categoryLable;

    @FXML
    private Button exit;

    @FXML
    private VBox itemDeatails;

    @FXML
    private TextArea itemDesc;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label priceLable;

    @FXML
    private Label quantityLable;

    @FXML
    private Label stateLabel;

    @FXML
    private ImageView userImage;

    @FXML
    private Label userName;



    private MyListener myListener;
    private Bien bien;

    public void setData(Bien bien,MyListener myListener) {
        this.bien=bien;
        this.myListener=myListener;
        userName.setText("Omar Salhi");
        itemImage.setImage(new Image(getClass().getResourceAsStream( bien.getImgSource())));
        itemDesc.setText( "qsfdgoauiehrtgpbea ufhgae ouifehg dfvb ae rhtgqfvhbj aert qfvhuaerçtg" );
        priceLable.setText( Float.toString(bien.getPrice()) );
        quantityLable.setText(Float.toString(bien.getQuantity())   );
        stateLabel.setText((bien.getState())?"In Stock":"Out Of Stock");
    }

    @FXML
    public void onExitBtnClicked(ActionEvent event){
       myListener.exit();
    }
}
