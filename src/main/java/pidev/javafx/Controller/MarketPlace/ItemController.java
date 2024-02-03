package pidev.javafx.Controller.MarketPlace;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.javafx.Model.MarketPlace.Bien;

public class ItemController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label categoryLable;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private HBox itemInfo;
    @FXML
    private AnchorPane anchorPaneItem;
    @FXML
    private VBox bascInfoItems;
    @FXML
    private Button trade;
    @FXML
    private Button info;
    @FXML
    private Button add2Card;

    private Bien bien;
    private MyListener myListener;

    @FXML
    public void onInfoBtnClicked(ActionEvent event){
        myListener.onClickListener(bien);
    }



    public void setData(Bien bien, MyListener myListener) {
        itemInfo.setPrefHeight(0);
        bascInfoItems.setPrefHeight(270);
        anchorPaneItem.setPrefHeight(274);
        this.bien = bien;
        System.out.println("Omar2");
        this.myListener = myListener;
        nameLabel.setText(bien.getName());
        priceLable.setText( "$"+bien.getPrice());
        quantityLabel.setText(Float.toString(bien.getQuantity()));
        categoryLable.setText(bien.getCategorie().name());
        Image image = new Image(getClass().getResourceAsStream(bien.getImgSource()));
        img.setImage(image);
        itemInfo.setVisible( false );
    }

    public void showTransitionInfo(Boolean state){
        if(state){
            anchorPaneItem.setPrefHeight( 274 );
            bascInfoItems.setPrefHeight( 220 );
            itemInfo.setPrefHeight(50 );
        }
        else{
            itemInfo.setPrefHeight( 0 );
            bascInfoItems.setPrefHeight( 270 );
            anchorPaneItem.setPrefHeight( 274 );
        }

        itemInfo.setVisible( state );

    }

}
