package pidev.javafx.Controller.MarketPlace;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import pidev.javafx.Controller.Crud.CrudProd;
import pidev.javafx.Controller.Tools.MyListener;
import pidev.javafx.Model.MarketPlace.Categorie;
import pidev.javafx.Model.MarketPlace.Product;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class MarketController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    @FXML
    private HBox mainHbox;
    @FXML
    private VBox hepfullBar;
    @FXML
    private Button addBien;
    @FXML
    private Button exitBtn;
    private VBox vBox;



    private List<Product> products = new ArrayList<>();
    private Image image;
    private MyListener myListener;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox=null;
        try {
            hepfullBar = FXMLLoader.load(getClass().getResource( "/fxml/marketPlace/helpfullBar.fxml" ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        showGridPane();
//        scroll.get
    }



    public void getProduct(AnchorPane item,ItemController itemController){
        item.hoverProperty().addListener( (observable, oldValue, show)->{
            itemController.showTransitionInfo( show );
        } );
    }



    public void animateChanges(Node node1, Node node2){
        FadeTransition fade1 = new FadeTransition( Duration.seconds( 0.4 ), node1);
        fade1.setFromValue( 1 );
        fade1.setToValue( 0 );
        FadeTransition fade2 = new FadeTransition( Duration.seconds( 0.4), node2 );
        fade2.setFromValue( 0 );
        fade2.setToValue( 0.99);

        fade1.play();
        fade1.setOnFinished(event ->{
            mainHbox.getChildren().remove( node1 );
            mainHbox.getChildren().add( node2 );
            fade2.play();
        });
    }



    public void showGridPane(){
        products= CrudProd.getInstance().selectItems();
        if (products.size() > 0) {
            try {
                hepfullBar = FXMLLoader.load(getClass().getResource( "/fxml/marketPlace/helpfullBar.fxml" ));
                mainHbox.getChildren().add(hepfullBar);
            } catch (IOException e) {
                throw new RuntimeException( e );
            }
            myListener = new MyListener<Product>() {
                @Override
                public void onClickListener(Product arg){
                    mainHbox.getChildren().remove(vBox);
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation( getClass().getResource( "/fxml/marketPlace/itemInfo.fxml" ) );
                        vBox = fxmlLoader.load();
                        ItemInfoController itemInfoController = fxmlLoader.getController();
                        myListener = new MyListener<Product>() {
                            @Override
                            public void exit() {
                                animateChanges(vBox,hepfullBar);
                            }
                        };
                        itemInfoController.setData( products.get( 0 ), myListener );
                        animateChanges(hepfullBar,vBox);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/marketPlace/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(products.get(i),myListener);
                getProduct(anchorPane,itemController);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.setHgap( 25 );
                grid.setVgap( 25 );

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
            }
            grid.setPrefHeight(670);
            grid.setPrefWidth(800);
            grid.setPadding( new Insets( -10,0,10,20 ));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
