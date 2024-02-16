package pidev.javafx.Controller.MarketPlace;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
<<<<<<< HEAD
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.javafx.Controller.MainWindowController;
import pidev.javafx.Controller.Tools.CustomMouseEvent;
import pidev.javafx.Controller.Tools.EventBus;
import pidev.javafx.Controller.Tools.MyListener;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Product;
=======
import pidev.javafx.entities.Marketplace.Bien;
import pidev.javafx.MyListener;
>>>>>>> Aziz_Gmaty_branch

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


    private Bien bien;
    private MyListener myListener;
    private HBox hbox;



    public void setData(Bien bien, MyListener myListener) {
        this.bien = bien;
        this.myListener = myListener;
        nameLabel.setText(bien.getName());
        priceLable.setText( "$"+bien.getPrice());
        stateLabel.setText((bien.getState())?"In Stock":"Out Of Stock");
        categoryLable.setText(bien.getCategorie().name());
        Image image = new Image(getClass().getResourceAsStream(bien.getImgSource()));
        img.setImage(image);
        hbox=createItemsBtns();
    }
    public void setData(Bien bien) {
        nameLabel.setText(bien.getName());
        priceLable.setText( "$"+bien.getPrice());
        stateLabel.setText((bien.getState())?"In Stock":"Out Of Stock");
        categoryLable.setText(bien.getCategorie().name());
        Image image = new Image(getClass().getResourceAsStream(bien.getImgSource()));
        img.setImage(image);
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


        add2Card.setOnMouseClicked( event -> {
            CustomMouseEvent<Product> customEvent = new CustomMouseEvent<>(bien);
            EventBus.getInstance().publish( "laodCheckOut",customEvent);
        });
        info.setOnMouseClicked( event -> myListener.onClickListener( bien ) );

        hbox.getChildren().addAll( add2Card,trade,info );
        hbox.setSpacing( 10 );
        hbox.setAlignment(Pos.CENTER);
        hbox.setId( "itemInfo" );
        hbox.getStylesheets().add( String.valueOf( getClass().getResource("/style/Buttons.css") ) );
        return hbox;
    }

}
