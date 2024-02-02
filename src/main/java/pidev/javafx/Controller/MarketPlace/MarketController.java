package pidev.javafx.Controller.MarketPlace;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Categorie;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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


    private List<Bien> biens = new ArrayList<>();
    private Image image;
    private MyListener myListener;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            hepfullBar = FXMLLoader.load(getClass().getResource( "/fxml/marketPlace/helpfullBar.fxml" ));
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("omar");
        }
        showGridPane();
        System.out.println("omar");
    }



    public void getProduct(AnchorPane item,ItemController itemController){
        item.hoverProperty().addListener( (observable, oldValue, show)->{
            itemController.showTransitionInfo( show );
        } );
    }



    private List<Bien> getData() {
        List<Bien> biens = new ArrayList<>();
        Bien bien;
        for(int i=0;i<12;i++){
            bien=new Bien(i,1,"Product_"+i,"/icons/"+i+".png",i*25f,20f,false,new Timestamp(System.currentTimeMillis()), Categorie.ENTERTAINMENT );
            biens.add( bien );
        }
        return biens;
    }



    public void showGridPane(){
        biens.addAll(getData());
        if (biens.size() > 0) {
            try {
                hepfullBar = FXMLLoader.load(getClass().getResource( "/fxml/marketPlace/helpfullBar.fxml" ));
            } catch (IOException e) {
                throw new RuntimeException( e );
            }
            mainHbox.getChildren().add(hepfullBar);
            myListener = new MyListener() {
                @Override
                public void onClickListener(Bien bien){
                    try {
                        VBox vBox = FXMLLoader.load(getClass().getResource( "/fxml/marketPlace/itemInfo.fxml" ));
                        mainHbox.getChildren().remove(hepfullBar);
                        mainHbox.getChildren().add(vBox );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < biens.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/marketPlace/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(biens.get(i),myListener);
                getProduct(anchorPane,itemController);
                if (column == 3) {
                    column = 0;
                    row++;
                }
//                grid.setGridLinesVisible( true );
                grid.setHgap( 40 );
                grid.setVgap( 40 );

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
//                GridPane.setMargin(anchorPane, new Insets(10));
            }
            grid.setPrefHeight(670);
            grid.setPrefWidth(760);
            grid.setPadding( new Insets( -10,20,10,20 ));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
