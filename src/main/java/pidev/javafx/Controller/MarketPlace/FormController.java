package pidev.javafx.Controller.MarketPlace;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev.javafx.Controller.Crud.CrudBien;
import pidev.javafx.Controller.Tools.MyListener;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Categorie;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class FormController implements Initializable {

    @FXML
    private ChoiceBox<Categorie> Pcategory;
    @FXML
    private TextArea Pdescretion;
    @FXML
    private TextField Pname;
    @FXML
    private TextField Pprice;
    @FXML
    private TextField Pquantity;
    @FXML
    private VBox formBox;
    @FXML
    private Button imageBtn;




//    BufferedImage bi;
    private File chosenFile;
    MyListener listener;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        formBox.getChildren().add( createFormBtns() );
        Pcategory.getItems().addAll( Categorie.values() );


        imageBtn.setOnAction( event -> {
            FileChooser fileChooser = new FileChooser();
            setExtFilters(fileChooser);
            fileChooser.setTitle("Save Image");
            chosenFile = fileChooser.showOpenDialog( Stage.getWindows().get(0) );
        } );

    }

    private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }



    public void onAddBienClicked() {
        Bien bien =new Bien( 0,
                1,
                Pname.getText(),
                Pdescretion.getText(),
                chosenFile.getAbsolutePath(),
                Float.parseFloat( Pprice.getText() ),
                Float.parseFloat( Pquantity.getText()),
                Boolean.TRUE,
                Timestamp.valueOf( LocalDateTime.now() ),
                Pcategory.getValue());
        CrudBien.getInstance().addItem(bien);
    }


    public HBox createFormBtns(){
        Button addProd= new Button();
        Button clearProd = new Button();
        Button cancel= new Button();

        HBox hbox=new HBox();

        addProd.setPrefWidth( 50 );
        clearProd.setPrefWidth( 50 );
        cancel.setPrefWidth( 50 );

        addProd.setPrefHeight( 32 );
        clearProd.setPrefHeight( 32 );
        cancel.setPrefHeight( 32 );

        Image img1= new Image(String.valueOf( getClass().getResource("/namedIcons/tab2.png") ));
        Image img2= new Image(String.valueOf( getClass().getResource("/namedIcons/broom.png")));
        Image img3= new Image(String.valueOf( getClass().getResource("/namedIcons/paper.png")));

        addProd.setGraphic( new ImageView( img1 ));
        clearProd.setGraphic( new ImageView( img2 ));
        cancel.setGraphic( new ImageView( img3 ));

        addProd.setOnMouseClicked( event -> onAddBienClicked() );
        clearProd.setOnMouseClicked( event -> {
            Pdescretion.setText( "" );
            Pname.setText( "" );
            Pprice.setText( "" );
            Pquantity.setText( "" );
        } );
        cancel.setOnMouseClicked( event -> listener.exit() );

        hbox.getChildren().addAll( addProd,clearProd,cancel );
        hbox.setSpacing( 20 );
        hbox.setAlignment( Pos.CENTER);
        hbox.setId( "itemInfo" );
        hbox.getStylesheets().add( String.valueOf( getClass().getResource("/style/Buttons.css") ) );

        return hbox;
    }

    public void setExitFunction(MyListener listener) {
        this.listener=listener;
    }

}






















//        int nameLenght=chosenFile.getName().length();
//        String fileName=Double.toString(chosenFile.getPath().length()* randomVal.nextInt(chosenFile.getPath().length())*nameLenght/2)+chosenFile.getName().substring(0,nameLenght-4);
//        String path ="usersImg/"+fileName+".png";
//
//
//        String sql = "INSERT INTO bien "
//                + "(idUser,name,imgSource,price,quantity,state,timestamp,category) "
//                + "VALUES(?,?,?,?,?,?,?,?)";
//
//        connect = ConnectionDB.connectDb();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            prepare.setString(1, "1");
//            prepare.setString(2, Pname.getText());
//            prepare.setString(3, path );
////                prepare.setString(4, String.valueOf(dob.getValue()));
//            prepare.setString(4, Pprice.getText());
//            prepare.setString(5, Pquantity.getText());
//            prepare.setString(6, "1");
//            prepare.setString(7, Timestamp.valueOf(LocalDateTime.now()).toString());
//            prepare.setString(8, Pcategory.getValue().toString());
//            prepare.executeUpdate();
//
//            try {
//                ImageIO.write(bi, "png", new File( "src/main/resources/"+path ));
//            } catch (IOException e) {
//                throw new RuntimeException( e );
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
