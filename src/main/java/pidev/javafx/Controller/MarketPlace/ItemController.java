package pidev.javafx.Controller.MarketPlace;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.javafx.Controller.Tools.MyListener;
import pidev.javafx.Model.MarketPlace.Product;

public class ItemController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label stateLabel;
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
    private VBox vboxItem;


    private Product product;
    private MyListener myListener;
    private HBox hbox;



    public void setData(Product product, MyListener myListener) {
        this.product = product;
        this.myListener = myListener;
        nameLabel.setText(product.getName());
        priceLable.setText( "$"+product.getPrice());
        stateLabel.setText((product.getState())?"In Stock":"Out Of Stock");
        categoryLable.setText(product.getCategorie().name());
        Image image = new Image(getClass().getResourceAsStream(product.getImgSource()));
        img.setImage(image);
        hbox=createItemsBtns();
    }

    public void showTransitionInfo(Boolean state){
        if(state){
            vboxItem.getChildren().remove( hbox );
            vboxItem.getChildren().add(hbox);
            vboxItem.setMargin(hbox,new Insets( 0,5,5,5 ) );
        }
        else
            vboxItem.getChildren().remove( hbox );
    }


    public HBox createItemsBtns(){
        Button add2Card= new Button();
        Button trade = new Button();
        Button info= new Button();

        HBox hbox=new HBox();

        trade.setPrefWidth( 50 );
        info.setPrefWidth( 50 );
        add2Card.setPrefWidth( 50 );

        trade.setPrefHeight( 32 );
        info.setPrefHeight( 32 );
        add2Card.setPrefHeight( 32 );

        Image img1= new Image(String.valueOf( getClass().getResource("/namedIcons/buy.png") ));
        Image img2= new Image(String.valueOf( getClass().getResource("/namedIcons/exchange.png")));
        Image img3= new Image(String.valueOf( getClass().getResource("/namedIcons/interface.png")));

        add2Card.setGraphic( new ImageView( img1 ));
        trade.setGraphic( new ImageView( img2 ));
        info.setGraphic( new ImageView( img3 ));


        info.setOnMouseClicked( event -> myListener.onClickListener( product ) );

        hbox.getChildren().addAll( add2Card,trade,info );
        hbox.setSpacing( 10 );
        hbox.setAlignment(Pos.CENTER);
        hbox.setId( "itemInfo" );
        hbox.getStylesheets().add( String.valueOf( getClass().getResource("/style/Buttons.css") ) );
        return hbox;
    }

}
