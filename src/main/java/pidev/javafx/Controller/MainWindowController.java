package pidev.javafx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private boolean isSideBarOpen;
    @FXML
    private Button sideBarBtn;

    @FXML
    private Button btn2;
    @FXML
    private Button marketPlaceBtn;

    @FXML
    private Button VoyageBtn;
    @FXML
    private Button GareBtn;
    @FXML
    private VBox sideBar;
    @FXML
    private VBox zipSideBar;
    @FXML
    private BorderPane mainBorderPain;
    @FXML
    private AnchorPane leftAnchorPane;
    @FXML
    private MenuButton menubottons;

    private ListView Transport_list;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
//    btns that changes the scenes
    @FXML
    public void onShowEmpClicked(ActionEvent event) throws IOException {
        VBox showEmpAnchorPane = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/fxml/marketPlace/showItems.fxml" ) ) );
        mainBorderPain.setCenter(showEmpAnchorPane);
    }

    @FXML
    public void onBtn2Clicked(ActionEvent event) throws IOException {
        AnchorPane showEmpAnchorPane = FXMLLoader.load(getClass().getResource( "/fxml/Employe/btn2.fxml" ));
        mainBorderPain.setCenter(showEmpAnchorPane);
    }

    @FXML
    public void onMarketPlaceBtnClicked(ActionEvent event) throws IOException {
        ScrollPane scrollPane = FXMLLoader.load(getClass().getResource( "/fxml/marketPlace/myMarket.fxml" ));
        scrollPane.setPrefHeight(mainBorderPain.getPrefHeight()  );
        scrollPane.setPrefWidth( mainBorderPain.getPrefWidth()-sideBar.getPrefWidth() );
        mainBorderPain.setCenter(scrollPane);
    }
    @FXML
    public void onTransportClicked(ActionEvent event) throws IOException {
        ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/Display_Transport.fxml")));
        scrollPane.setPrefHeight(mainBorderPain.getPrefHeight()  );
        scrollPane.setPrefWidth( mainBorderPain.getPrefWidth()-sideBar.getPrefWidth() );
        mainBorderPain.setCenter(scrollPane);
    }
    @FXML
    public void onGareClicked(ActionEvent event) throws IOException{
       // ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/Display_Transport.fxml")));
        ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/Abonnement.fxml")));

        scrollPane.setPrefHeight(mainBorderPain.getPrefHeight() );
        scrollPane.setPrefWidth( mainBorderPain.getPrefWidth()-sideBar.getPrefWidth() );
        mainBorderPain.setCenter(scrollPane);

}
@FXML
    public void onStationClicked(ActionEvent event) throws IOException{
    ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull( getClass().getResource("/fxml/Transport/Station.fxml")));

    scrollPane.setPrefHeight(mainBorderPain.getPrefHeight() );
    scrollPane.setPrefWidth( mainBorderPain.getPrefWidth()-sideBar.getPrefWidth() );
    mainBorderPain.setCenter(scrollPane);
}
}



