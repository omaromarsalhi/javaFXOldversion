package pidev.javafx.Controller.UserMarketDashbord;

import javafx.animation.PauseTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pidev.javafx.Controller.MarketPlace.FormController;
import pidev.javafx.Controller.MarketPlace.ItemController;
import pidev.javafx.Controller.MarketPlace.ItemInfoController;
import pidev.javafx.Controller.MarketPlace.MyListener;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Categorie;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class MainDashbordController implements Initializable {

    @FXML
    private TableColumn<Bien, String> descCol;
    @FXML
    private TableColumn<Bien, String> imgCol;
    @FXML
    private TableColumn<Bien, String> nameCol;
    @FXML
    private TableColumn<Bien, Float> priceCol;
    @FXML
    private TableColumn<Bien, Float> quantityCol;
    @FXML
    private TableColumn<Bien, Boolean> stateCol;
    @FXML
    private TableColumn<Bien, Timestamp> timestampCol;
    @FXML
    private TableColumn<Bien, Categorie> categoryCol;
    @FXML
    private TableView<Bien> ProductTable;
    @FXML
    private AnchorPane informationBar;
    @FXML
    private AnchorPane accountInfo;
    @FXML
    private Button updateBtn;
    @FXML
    private Button createBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private HBox searchHbox;
    @FXML
    private TextField searchTextField;


    private VBox infoTemplate;
    private ItemInfoController infoTemplateController;
    private Timer animTimer;
    EventHandler<MouseEvent> eventHandler;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("descreption"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        timestampCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        ProductTable.setItems(getData());

        PauseTransition pause = new PauseTransition( Duration.seconds(0.1));
        pause.setOnFinished(e -> {
            loadInfoTemplate();
            loadInfoOfSpecificItem(ProductTable.getItems().get(0));
            informationBar.getChildren().add(infoTemplate);
        });
        pause.play();

        ProductTable.setOnMouseClicked( event -> {
            loadInfoOfSpecificItem(ProductTable.getSelectionModel().getSelectedItem());
        } );


        animTimer = new Timer();
        searchTextField.setVisible( false );
        searchBtn.setStyle( "-fx-border-radius: 20;" );

        eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getEventType());
                animateSearchBar(String.valueOf( event.getEventType() ) );
            }
        };
        searchHbox.setOnMouseEntered(eventHandler);
    }



    public void animateSearchBar(String eventType){
        if(eventType.equals("MOUSE_ENTERED")){
            animTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(searchTextField.getWidth()==16){
                        searchHbox.setOnMouseEntered(null);
                        searchHbox.setOnMouseExited(null);
                        searchBtn.setStyle( "-fx-border-radius: 0 20 20 0;" +
                                "-fx-border-color: black  black black transparent ;");
                        searchTextField.setVisible( true );
                    }
                    if (searchTextField.getWidth()<(searchHbox.getWidth()-searchBtn.getWidth()-20)) {
                        searchTextField.setPrefWidth(searchTextField.getWidth()+10);
                    } else {
                        searchHbox.setOnMouseExited(eventHandler);
                        this.cancel();
                    }
                }

            }, 500, 20);
        }
        else if(eventType.equals("MOUSE_EXITED")){
            animTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                        if (searchTextField.getWidth() <=16) {
                            searchHbox.setOnMouseEntered( null );
                            searchHbox.setOnMouseExited( null );
                            searchBtn.setStyle( "-fx-border-color: black;");
                            searchBtn.setStyle( "-fx-border-radius: 20;");
                            searchTextField.setVisible( false );
                        }
                        if (searchTextField.getWidth() > 16) {
                            searchTextField.setPrefWidth( searchTextField.getWidth() - 10 );
                        } else {
                            searchHbox.setOnMouseEntered( eventHandler );
                            this.cancel();
                        }
                }
            }, 1000, 15);
        }
    }
    @FXML
    public void onCreateBtnClicked(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/marketPlace/form.fxml"));
        VBox form = null;
        try {
            form = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        FormController formController = fxmlLoader.getController();
        informationBar.getChildren().remove(infoTemplate);
        informationBar.getChildren().add(form);
    }



    public void loadInfoTemplate() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/marketPlace/itemInfo.fxml"));
        infoTemplate = null;
        try {
            infoTemplate = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        infoTemplateController = fxmlLoader.getController();
    }

    public void loadInfoOfSpecificItem(Bien bien) {
        infoTemplateController.setDataForLocalUser(bien,(accountInfo.getWidth()/2));
        infoTemplate.setPrefHeight( informationBar.getPrefHeight());
    }


    public ObservableList<Bien> getData() {
        ObservableList<Bien> biens = FXCollections.observableArrayList();
        for(int i=0;i<40;i++){
            biens.add(new Bien(i,1,"Product_"+i,"zetgrtgh ergh ey hnrtuj yuikj,r tyhn","/icons/"+i+".png",i*25f,20f,false,new Timestamp(System.currentTimeMillis()), Categorie.ENTERTAINMENT ) );
        }
        biens.get( 0 ).setImgSource( "/img/banana.png" );
        biens.get( 1 ).setImgSource( "/img/cherry.png" );
        biens.get( 2 ).setImgSource( "/img/coconut.png" );
        biens.get( 3 ).setImgSource( "/img/grapes.png" );
        biens.get( 4).setImgSource( "/img/ic_cart.png" );
        biens.get( 5 ).setImgSource( "/img/ic_delivery.png" );
        biens.get( 6 ).setImgSource( "/img/kiwi.png" );
        biens.get( 7 ).setImgSource( "/img/mango.png" );

        for(int i=0;i<40;i++){
            biens.get(i).setImage(new ImageView(new Image(getClass().getResourceAsStream(biens.get(i).getImgSource()),35,35,false,false)));
        }
        return biens;
    }
}
