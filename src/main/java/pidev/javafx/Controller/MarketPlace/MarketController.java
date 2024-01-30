package pidev.javafx.Controller.MarketPlace;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Categorie;
import pidev.javafx.MyListener;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
//    @FXML
//    private VBox chosenFruitCard;
//
//    @FXML
//    private Label fruitNameLable;
//
//    @FXML
//    private Label fruitPriceLabel;
//
//    @FXML
//    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;
    @FXML
    private Button addBien;
    @FXML
    private GridPane grid;

    private List<Bien> biens = new ArrayList<>();
    private Image image;
    private MyListener myListener;


    private List<Bien> getData() {
        List<Bien> biens = new ArrayList<>();
        Bien bien;
        for(int i=0;i<12;i++){
            bien=new Bien(i,1,"Product_"+i,"/icons/"+i+".png",i*25f,20f,false,new Timestamp(System.currentTimeMillis()), Categorie.ENTERTAINMENT );
            biens.add( bien );
        }
        return biens;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        biens.addAll(getData());
//        if (fruits.size() > 0) {
//            setChosenFruit(fruits.get(0));
//            myListener = new MyListener() {
//                @Override
//                public void onClickListener(Fruit fruit) {
//                    setChosenFruit(fruit);
//                }
//            };
//        }
        int column = 0;
        int row = 1;
        try {
            System.out.println(biens.size());
            for (int i = 0; i < biens.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/marketPlace/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(biens.get(i),myListener);

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
