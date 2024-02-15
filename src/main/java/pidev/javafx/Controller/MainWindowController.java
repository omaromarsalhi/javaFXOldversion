package pidev.javafx.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.w3c.dom.NodeList;
import pidev.javafx.Controller.Contrat.CheckOutController;
import pidev.javafx.Controller.MarketPlace.ItemController;
import pidev.javafx.Controller.Tools.CustomMouseEvent;
import pidev.javafx.Controller.Tools.EventBus;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private boolean isSideBarOpen;
    @FXML
    private Button sideBarBtn;
    @FXML
    private Button showEmp;
    @FXML
    private Button btn2;
    @FXML
    private Button marketPlaceBtn;
    @FXML
    private VBox sideBar;
    @FXML
    private VBox zipSideBar;
    @FXML
    private Button MPD;
    @FXML
    private BorderPane mainBorderPain;
    @FXML
    private AnchorPane MainAnchorPane;
    @FXML
    private MenuButton menubottons;


<<<<<<< HEAD

=======
    private HBox mainhBox;
>>>>>>> 2bfe4276af099b37f7920e215c3cd04636278f5f
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventBus.getInstance().subscribe( "laodCheckOut",this::laodCheckOut );
        EventBus.getInstance().subscribe( "laodMarketPlace",this::onMarketPlaceBtnClicked );
    }
//    btns that changes the scenes
    @FXML
    public void onShowEmpClicked(ActionEvent event) throws IOException {
        VBox showEmpAnchorPane = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/fxml/marketPlace/showItems.fxml" ) ) );
        mainBorderPain.setCenter(showEmpAnchorPane);
    }

    @FXML
<<<<<<< HEAD
    public void onBtn2Clicked(ActionEvent event) throws IOException {
        AnchorPane showEmpAnchorPane = FXMLLoader.load(getClass().getResource( "/fxml/Demande/menuDemande.fxml" ));
        mainBorderPain.setCenter(showEmpAnchorPane);
=======
    public void onMPDClicked(ActionEvent event) throws IOException {
        HBox hBox = FXMLLoader.load(getClass().getResource( "/fxml/userMarketDashbord/userMainDashbord.fxml" ));
        mainBorderPain.setCenter(hBox);
>>>>>>> 2bfe4276af099b37f7920e215c3cd04636278f5f
    }

    @FXML
    public void onMarketPlaceBtnClicked(ActionEvent event){
        mainBorderPain.getChildren().remove(mainhBox);
        try {
            mainhBox = FXMLLoader.load(getClass().getResource( "/fxml/marketPlace/myMarket.fxml" ));
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        mainhBox.setMaxHeight(MainAnchorPane.getPrefHeight()  );
        mainhBox.setMaxWidth( MainAnchorPane.getPrefWidth());
        mainBorderPain.setCenter(mainhBox);
    }

    public void laodCheckOut(CustomMouseEvent<Bien> event) {
        mainBorderPain.getChildren().remove(mainhBox);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/Contrat/checkOut.fxml"));
            mainhBox = fxmlLoader.load();
            CheckOutController checkOutController = fxmlLoader.getController();
            checkOutController.setData(event.getEventData());
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        mainhBox.setMaxHeight(MainAnchorPane.getPrefHeight()  );
        mainhBox.setMaxWidth( MainAnchorPane.getPrefWidth());
        mainBorderPain.setCenter(mainhBox);
    }

}
