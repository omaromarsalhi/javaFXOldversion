package pidev.javafx.Controller.MarketPlace;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.javafx.Controller.Tools.MyListener;
import pidev.javafx.Model.MarketPlace.Product;

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
    private Label prodName;
    @FXML
    private Label userName;
    @FXML
    private HBox userInfo;



    private MyListener myListener;
    private Product product;

    public void setData(Product product,MyListener myListener) {
        this.product=product;
        this.myListener=myListener;
        userName.setText("Omar Salhi");
        prodName.setText( product.getName() );
        itemImage.setImage(new Image(getClass().getResourceAsStream( product.getImgSource())));
        itemDesc.setText( "qsfdgoauiehrtgpbea ufhgae ouifehg dfvb ae rhtgqfvhbj aert qfvhuaerçtg" );
        priceLable.setText( Float.toString(product.getPrice()) );
        quantityLable.setText(Float.toString(product.getQuantity())   );
        stateLabel.setText((product.getState())?"In Stock":"Out Of Stock");
    }

    public void setDataForLocalUser(Product product, double width) {
        itemDeatails.getChildren().remove(exit);
        itemDeatails.getChildren().remove(userInfo);
        itemDeatails.setPrefHeight( itemDeatails.getPrefHeight()-100 );
        this.product=product;
        prodName.setStyle( "-fx-font-size: 20;" );
        prodName.setText( product.getName().toUpperCase() );

        itemImage.setFitWidth( width );
        if(!product.getImgSource().isEmpty())
            itemImage.setImage(new Image(getClass().getResourceAsStream( product.getImgSource()),width,width-20,false,false));
        itemDesc.setText( product.getDescreption() );
        priceLable.setText( Float.toString(product.getPrice()) );
        quantityLable.setText(Float.toString(product.getQuantity())   );
        stateLabel.setText((product.getState())?"In Stock":"Out Of Stock");
    }

    @FXML
    public void onExitBtnClicked(ActionEvent event){
       myListener.exit();
    }
}
