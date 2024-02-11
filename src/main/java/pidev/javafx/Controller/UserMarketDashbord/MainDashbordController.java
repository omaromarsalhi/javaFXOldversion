package pidev.javafx.Controller.UserMarketDashbord;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pidev.javafx.Controller.Crud.CrudBien;
import pidev.javafx.Controller.MarketPlace.*;
import pidev.javafx.Controller.Tools.CustomMouseEvent;
import pidev.javafx.Controller.Tools.EventBus;
import pidev.javafx.Controller.Tools.MyListener;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Product;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainDashbordController implements Initializable {


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
    @FXML
    private VBox showAllProdsInfo;


    private VBox infoTemplate;
    private ItemInfoController infoTemplateController;
    private Timer animTimer;
    private EventHandler<MouseEvent> eventHandler;
    private Product prod2Update;
    private TableView<Bien> tableViewProd;
    private TableViewController tableViewController;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadTableView();
        showAllProdsInfo.getChildren().add(tableViewProd);

        setMenueBar();

        PauseTransition pause = new PauseTransition( Duration.seconds(0.1));
        pause.setOnFinished(e -> {
            loadInfoTemplate();
            loadInfoOfSpecificItem(tableViewProd.getItems().get(0));
            informationBar.getChildren().addAll(infoTemplate);
        });
        pause.play();

        tableViewProd.setOnMouseClicked( event -> {
            loadInfoOfSpecificItem(tableViewProd.getSelectionModel().getSelectedItem());
        } );

        animTimer = new Timer();
        searchTextField.setVisible( false );
        searchBtn.setStyle( "-fx-border-radius: 20;" );

        eventHandler = event -> {
            animateSearchBar(String.valueOf( event.getEventType() ) );
        };

        searchHbox.setOnMouseEntered(eventHandler);

        EventBus.getInstance().subscribe( "refreshTableOnDelete",this::refreshTableOnDelete );
        EventBus.getInstance().subscribe( "refreshTableOnAddOrUpdate",this::refreshTableOnAddOrUpdate );
        EventBus.getInstance().subscribe( "updateProd",this::doUpdate );



    }



    public void setMenueBar(){
        var addProduct=new MenuItem("Add Prod",new ImageView(new Image(getClass().getResourceAsStream("/namedIcons/more.png"))));
        var showForSaleProduct=new MenuItem("Show  My Prod",new ImageView(new Image(getClass().getResourceAsStream("/namedIcons/database.png"))));
        var showForPushasedProduct=new MenuItem("Show Purshased Prod",new ImageView(new Image(getClass().getResourceAsStream("/namedIcons/database.png"))));
        addProduct.setOnAction( event -> setFormForAddOrUpdate("add_prod") );
        showForSaleProduct.setOnAction( event -> {
            showAllProdsInfo.getChildren().clear();
            showAllProdsInfo.getChildren().add(tableViewProd);
        } );
        showForPushasedProduct.setOnAction( event -> createView() );

        menuBar.getMenus().get( 0 ).getItems().addAll(addProduct ,showForSaleProduct,showForPushasedProduct);

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

    public void doUpdate(CustomMouseEvent<Product> customMouseEvent){
        prod2Update=customMouseEvent.getEventData();
        setFormForAddOrUpdate("update_prod");
    }

    public void createView(){

        showAllProdsInfo.getChildren().remove( tableViewProd );
        ObservableList<Bien> liestBien=CrudBien.getInstance().selectItems();

        HBox hBox=new HBox();

        for(int i=0;i< liestBien.size();i++){

            if(i%2==0)
                hBox=new HBox();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource( "/fxml/Contrat/tarnsactionDetails.fxml" ));
            StackPane stackPane = null;
            FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader2.setLocation(getClass().getResource("/fxml/marketPlace/item.fxml"));
            AnchorPane anchorPane = null;
            try {
                stackPane = fxmlLoader.load();
                anchorPane = fxmlLoader2.load();
            } catch (IOException e) {
                throw new RuntimeException( e );
            }

            ItemController itemController = fxmlLoader2.getController();
            itemController.setData(liestBien.get( i ));

            AnchorPane finalAnchorPane = anchorPane;
            AtomicBoolean val= new AtomicBoolean( true );
            stackPane.setOnMouseClicked( event -> {
                if(val.get()){
                    animateProdBox(1,finalAnchorPane);
                    val.set( false );
                }
                else{
                    animateProdBox(0,finalAnchorPane);
                    val.set( true );
                }
            } );




            stackPane.getChildren().add( anchorPane );
            hBox.setPadding( new Insets( 20,80,20,80 ) );
            hBox.getChildren().add( stackPane );
            hBox.setSpacing( 50 );
            hBox.setAlignment( Pos.CENTER );

            if(i%2==0&&i!=0)
                showAllProdsInfo.getChildren().add(hBox);
        }
    }
    public void animateProdBox(int initialState,Node node){
        FadeTransition fade = new FadeTransition( Duration.seconds( 0.8),node  );
        fade.setFromValue(initialState);
        fade.setToValue(1-initialState);
        fade.play();
    }


    public void setFormForAddOrUpdate(String termOfUse){
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
                loadInfoOfSpecificItem(tableViewProd.getItems().get(0));
                informationBar.getChildren().add(infoTemplate);
            }
        };
        formController.setExitFunction(listeener);
        if(termOfUse.equals( "update_prod" ))
            formController.setInformaton( prod2Update );
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

    }

    public void refreshTableOnDelete(CustomMouseEvent<Bien> event){
        tableViewProd.getItems().remove( event.getEventData() );
        tableViewProd.refresh();
        loadInfoOfSpecificItem(tableViewProd.getItems().get(0));
        tableViewProd.getSelectionModel().clearSelection();
    }

    public void refreshTableOnAddOrUpdate(MouseEvent event){
        tableViewProd.getItems().clear();
        tableViewController.setData(CrudBien.getInstance().selectItems());
        loadInfoOfSpecificItem(tableViewProd.getItems().get(0));
        tableViewProd.getSelectionModel().clearSelection();
    }


    public void loadTableView() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/userMarketDashbord/tableView.fxml"));
        tableViewProd = null;
        try {
            tableViewProd = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        tableViewController = fxmlLoader.getController();
        tableViewController.setData(CrudBien.getInstance().selectItems());
    }



}
