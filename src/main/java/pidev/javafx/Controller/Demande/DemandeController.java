package pidev.javafx.Controller.Demande;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DemandeController {

    public Pane box1;

    public Button showmore;
    public Button showmore1;
    public Button showmore2;

    public Pane box2;
    public Pane box3;


    public void  getBoton_on_click_add() {
        box1.setOnMouseEntered(event -> showmore.setVisible(true));
        box1.setOnMouseExited(event -> showmore.setVisible(false));
        //boton_on_click_add.setVisible(true);
    }
    public void  getBoton_on_click_add1() {
        box2.setOnMouseEntered(event -> showmore1.setVisible(true));
        box2.setOnMouseExited(event -> showmore1.setVisible(false));
        //boton_on_click_add.setVisible(true);
    }
    public void  getBoton_on_click_add2() {
        box3.setOnMouseEntered(event -> showmore2.setVisible(true));
        box3.setOnMouseExited(event -> showmore2.setVisible(false));
        //boton_on_click_add.setVisible(true);
    }
}


