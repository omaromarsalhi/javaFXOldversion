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
import pidev.javafx.MyListener;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    @FXML
    private LineChart<?,?> lineChart;
    @FXML
    private StackedBarChart<?, ?> stachBarChart;
    @FXML
    private Button addBien;


    private List<Bien> biens = new ArrayList<>();
    private Image image;
    private MyListener myListener;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showGridPane();
        getCharts();
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

    private void getCharts() {
        lineChart.getYAxis().setLabel("Product Number");
        var serie = new XYChart.Series();
        serie.setName("This week");
        serie.getData().add(new XYChart.Data("Mo", 100 ));
        serie.getData().add(new XYChart.Data("Tu", 45 ));
        serie.getData().add(new XYChart.Data("We", 20 ));
        serie.getData().add(new XYChart.Data("Th", 110 ));
        serie.getData().add(new XYChart.Data("Fr", 60 ));
        serie.getData().add(new XYChart.Data("Sa", 66 ));
        serie.getData().add(new XYChart.Data("Su", 13 ));

        lineChart.getData().addAll(serie);
        lineChart.getStylesheets().add( String.valueOf( getClass().getResource("/style/lineChartStyle.css") ) );


        stachBarChart.getYAxis().setLabel("value in $");
        var selled = new XYChart.Series();
        var purshased = new XYChart.Series();
        var traded = new XYChart.Series();

        selled.setName("Selled");
        selled.getData().add(new XYChart.Data("Mo", 100 ));
        selled.getData().add(new XYChart.Data("Tu", 45 ));
        selled.getData().add(new XYChart.Data("We", 20 ));
        selled.getData().add(new XYChart.Data("Th", 110 ));
        selled.getData().add(new XYChart.Data("Fr", 60 ));
        selled.getData().add(new XYChart.Data("Sa", 66 ));
        selled.getData().add(new XYChart.Data("Su", 13 ));

        purshased.setName("Purshased");
        purshased.getData().add(new XYChart.Data("Mo", 10 ));
        purshased.getData().add(new XYChart.Data("Tu", 80 ));
        purshased.getData().add(new XYChart.Data("We", 70 ));
        purshased.getData().add(new XYChart.Data("Th", 31 ));
        purshased.getData().add(new XYChart.Data("Fr", 16 ));
        purshased.getData().add(new XYChart.Data("Sa", 120 ));
        purshased.getData().add(new XYChart.Data("Su", 95 ));

        traded.setName("Traded");
        traded.getData().add(new XYChart.Data("Mo", 59 ));
        traded.getData().add(new XYChart.Data("Tu", 45 ));
        traded.getData().add(new XYChart.Data("We", 73 ));
        traded.getData().add(new XYChart.Data("Th", 110 ));
        traded.getData().add(new XYChart.Data("Fr", 120 ));
        traded.getData().add(new XYChart.Data("Sa", 88 ));
        traded.getData().add(new XYChart.Data("Su", 14 ));


        stachBarChart.getData().addAll(selled,purshased,traded);
        stachBarChart.getStylesheets().add( String.valueOf( getClass().getResource("/style/lineChartStyle.css") ) );

    }
    public void showGridPane(){
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
