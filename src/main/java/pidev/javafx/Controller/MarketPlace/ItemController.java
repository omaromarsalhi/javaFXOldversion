package pidev.javafx.Controller.MarketPlace;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Categorie;
import pidev.javafx.Model.MarketPlace.Fruit;
import pidev.javafx.MyListener;

public class ItemController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
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

//    @FXML
//    private void click(MouseEvent mouseEvent) {
//        myListener.onClickListener(fruit);
//    }

    private Bien bien;
//    private MyListener myListener;

    public void setData(Bien bien, MyListener myListener) {
        itemInfo.setPrefHeight( 0 );
        bascInfoItems.setPrefHeight( 330 );
        anchorPaneItem.setPrefHeight( 340 );
        this.bien = bien;
//        this.myListener = myListener;
        nameLabel.setText(bien.getName());
        priceLable.setText( "$"+bien.getPrice());
        quantityLabel.setText(Float.toString(bien.getQuantity()));
        firstNameLabel.setText( "Omar" );
        lastNameLabel.setText("Salhi"  );
        categoryLable.setText(bien.getCategorie().name());
        Image image = new Image(getClass().getResourceAsStream(bien.getImgSource()));
        img.setImage(image);
        itemInfo.setVisible( false );
    }
    public void showTransitionInfo(Boolean state){
        if(state){
            anchorPaneItem.setPrefHeight( 360 );
            bascInfoItems.setPrefHeight( 308 );
            itemInfo.setPrefHeight(55 );
        }
        else{
            itemInfo.setPrefHeight( 0 );
            bascInfoItems.setPrefHeight( 330 );
            anchorPaneItem.setPrefHeight( 340 );
        }

        itemInfo.setVisible( state );

    }

}
