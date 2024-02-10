package pidev.javafx.Controller.UserMarketDashbord;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pidev.javafx.Controller.Crud.CrudBien;
import pidev.javafx.Controller.MarketPlace.*;
import pidev.javafx.Controller.Tools.MyListener;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Categorie;
import pidev.javafx.Model.MarketPlace.Product;

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
    private VBox informationBar;
    @FXML
    private AnchorPane accountInfo;
    @FXML
    private Button searchBtn;
    @FXML
    private HBox searchHbox;
    @FXML
    private TextField searchTextField;
    @FXML
    private MenuBar menuBar;
    @FXML
    private VBox helperBar;


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

        ProductTable.setItems( CrudBien.getInstance().selectItems());

        setMenueBar();

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

        eventHandler = event -> {
            animateSearchBar(String.valueOf( event.getEventType() ) );
        };
        searchHbox.setOnMouseEntered(eventHandler);
    }



    public void setMenueBar(){
        var addProduct=new MenuItem("Add Prod",new ImageView(new Image(getClass().getResourceAsStream("/namedIcons/more.png"))));
        var showProduct=new MenuItem("Show Prod",new ImageView(new Image(getClass().getResourceAsStream("/namedIcons/database.png"))));
        addProduct.setOnAction( event -> {
            setFormForAdd();
        } );
        menuBar.getMenus().get( 0 ).getItems().addAll(addProduct ,showProduct);

        var addService=new MenuItem("Add Service",new ImageView(new Image(getClass().getResourceAsStream("/namedIcons/more.png"))));
        var showService=new MenuItem("Show Service",new ImageView(new Image(getClass().getResourceAsStream("/namedIcons/database.png"))));
        menuBar.getMenus().get( 1 ).getItems().addAll(addService ,showService);
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





    public void setFormForAdd(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/marketPlace/secondForm.fxml"));
        VBox form = null;
        try {
            form = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        FormController formController = fxmlLoader.getController();
        VBox finalForm = form;
        MyListener listeener=new MyListener() {
            @Override
            public void exit() {
                informationBar.getChildren().remove( finalForm );
                loadInfoTemplate();
                loadInfoOfSpecificItem(ProductTable.getItems().get(0));
                informationBar.getChildren().add(infoTemplate);
            }
        };
        formController.setExitFunction(listeener);
        informationBar.getChildren().remove(infoTemplate);
        form.setPrefHeight(informationBar.getPrefHeight());
        form.setPrefWidth(informationBar.getPrefWidth());
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

    public void loadInfoOfSpecificItem(Product product) {
        infoTemplateController.setDataForLocalUser(product,(accountInfo.getWidth()/2));
        infoTemplate.setPrefHeight( informationBar.getPrefHeight()-40);
//        infoTemplate.setPrefWidth( informationBar.getPrefWidth()-20);
    }

}
