package pidev.javafx.Controller.UserMarketDashbord;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pidev.javafx.Controller.Tools.CustomMouseEvent;
import pidev.javafx.Controller.Tools.EventBus;
import pidev.javafx.Controller.Tools.MyTools;
import pidev.javafx.Model.Contrat.Contract;
import pidev.javafx.Model.Contrat.PaymentMethod;
import pidev.javafx.Model.MarketPlace.Transaction;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class TransactionDetailsController   {

    @FXML
    private VBox contratDetails;
    @FXML
    private Label Ldate;

    @FXML
    private Label LpaymentMode;

    @FXML
    private Label Lprice;

    @FXML
    private Label Llocation;
    @FXML
    private Button generatePdfBtn;


    @FXML
    private Button deleteBtn;



    public void  setData(Transaction transaction, Contract contract){
        Lprice.setText( String.valueOf( transaction.getQuantity()*transaction.getPricePerUnit() )+"$" );
        LpaymentMode.setText(contract.getPaymentMethod().toString() );
        Ldate.setText( DateTimeFormatter.ofPattern("dd/MM/yy hh:mm").format(transaction.getEffectiveDate().toLocalDateTime())  );
        Llocation.setText( contract.getRecingLocation() );
    }

    public Button  getDownloadBtn() {
        return generatePdfBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }





}
