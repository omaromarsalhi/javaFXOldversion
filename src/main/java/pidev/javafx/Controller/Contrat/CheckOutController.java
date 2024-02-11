package pidev.javafx.Controller.Contrat;

import com.itextpdf.text.Paragraph;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev.javafx.Controller.Crud.CrudContract;
import pidev.javafx.Controller.Crud.CrudTransaction;
import pidev.javafx.Controller.Tools.EventBus;
import pidev.javafx.Model.Contrat.Contract;
import pidev.javafx.Model.Contrat.PaymentMethod;
import pidev.javafx.Model.MarketPlace.Bien;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import pidev.javafx.Model.MarketPlace.Transaction;
import pidev.javafx.Model.MarketPlace.TransactionMode;

import java.io.File;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class CheckOutController implements Initializable {

    @FXML
    private TextField Plocarion;
    @FXML
    private Label Pcategroy;
    @FXML
    private DatePicker PdateTime;
    @FXML
    private TextArea Pdesc;
    @FXML
    private ImageView Pimg;
    @FXML
    private Label Pname;
    @FXML
    private ChoiceBox<PaymentMethod> PpayementChoice;
    @FXML
    private Label Pprice;
    @FXML
    private Label Pquantity;
    @FXML
    private TextArea ProlesAndTerms;
    @FXML
    private Label Pstate;
    @FXML
    private Button exit;
    @FXML
    private Button generatePDFbtn;
    @FXML
    private VBox itemInfo;
    @FXML
    private HBox mainHbox;
    @FXML
    private TextField requestedQuantity;

    private Bien bien;
    private Contract contract;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contract =CrudContract.getInstance().selectLastItem();
        System.out.println(contract);
        exit.setOnAction( event -> EventBus.getInstance().publish( "laodMarketPlace", event ) );
        generatePDFbtn.setOnAction( event -> {
            bien.setQuantity( Float.parseFloat( Pquantity.getText() ) );
            contract =prepareContrat();
            generatePDF();
            CrudContract.getInstance().addItem( contract );
            contract =CrudContract.getInstance().selectLastItem();
            System.out.println(contract);
            CrudTransaction.getInstance().addItem( prepareTransaction() );
        });
        PpayementChoice.getItems().addAll( PaymentMethod.values());
    }

    public void setData(Bien bien) {
        this.bien=bien;
        Pname.setText( bien.getName() );
        Pcategroy.setText( bien.getCategorie().toString() );
        Pimg.setImage( new Image( getClass().getResourceAsStream( bien.getImgSource() ) ) );
        Pdesc.setText( bien.getDescreption() );
        Pprice.setText( Float.toString( bien.getPrice() ) );
        Pquantity.setText( Float.toString( bien.getQuantity() ) );
        requestedQuantity.setText( Float.toString( bien.getQuantity() ) );
        Pstate.setText( (bien.getState()) ? "In Stock" : "Out Of Stock" );
        ProlesAndTerms.setText( "1/iurf airfgyu &irfuh airfu azirhf arf_ azr ifarifu\n" +
                "2/kjhfv aeirugyh aeriguh zeriuuv zaeiurh gvaeur gh ufv\n" +
                "3/aiyuzr aizuyryfg aoiuyrf \n"+
                "4/aiyuzr aizuyryfg aoiuyrf rrtyhzyjz ztey rtyh\n" +
                "5/aiyuzr aizuyryfg  , ftujse qrgztheyik brghert \n" );
    }

    public Contract prepareContrat(){
        return new Contract(
                0,
                "Contrat of selling "+bien.getName(),
                LocalDateTime.now().toString(),
                String.valueOf(PdateTime.getValue()),
                "Buyinf this Item",
                ProlesAndTerms.getText(),
                PpayementChoice.getValue(),
                Plocarion.getText()
        );
    }


    public Transaction prepareTransaction(){
        return new Transaction(
                0,
                bien.getId(),
                contract.getIdContract(),
                1,
                2,
                bien.getPrice(),
                (int)Float.parseFloat( Pquantity.getText() ),
                TransactionMode.SELL,
                new Timestamp(new Date().getTime())
        );
    }

    public void generatePDF() {
                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(getFileOfSave()));
                    document.open();
                    document.add(new Paragraph("Title: Sale contrat" ));
                    document.add(new Paragraph("Party A ID: " + 1));
                    document.add(new Paragraph("Party B ID: " + 2));
                    document.add(new Paragraph("Item Name: " ));
                    document.add(new Paragraph("Effective Date: " + contract.getEffectiveDate()));
                    document.add(new Paragraph("Termination Date: " + contract.getTerminationDate()));
                    document.add(new Paragraph("Purpose: buyintg oéjrhgniuoh't" ));
                    document.add(new Paragraph("Terms and Conditions: " + contract.getTermsAndConditions()));
                    document.add(new Paragraph("Payment Method: " + contract.getPaymentMethod()));
                    document.close();
                    System.out.println("PDF generated successfully!");
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

    private File getFileOfSave(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pdf Image");
        fileChooser.setInitialDirectory( new File( "src/main/resources/Cnotrat" ) );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf")
        );
        return fileChooser.showSaveDialog( Stage.getWindows().get(0) );
    }

}
