package pidev.javafx.Controller.User;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.PipedReader;
import java.time.LocalDate;
import java.util.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



import java.net.URL;


import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import pidev.javafx.Model.user.Role;
import pidev.javafx.Model.user.User;

public class listUserController implements Initializable {
    @FXML
    private VBox Userlayout;
    @FXML
    private TextField bar_recherche;
    @FXML
    private VBox vboxfiltre;
    @FXML
    private VBox vboxinfo;
    @FXML
    private TextField firstname;
    @FXML
    private
    TextField email;
    @FXML
    private TextField lastname;

    @FXML
    private TextField cin;

    @FXML
    private TextField adresse;

    @FXML
    private TextField age;

    @FXML
    private TextField status;

    @FXML
    private TextField phone;

    @FXML
    private Label role;

    @FXML
    private DatePicker date;

    @FXML
    private DatePicker dob;

    @FXML
    private Button btn_modif;

    @FXML
    private Button btn_supp;

    @FXML
    private Button btn_bloq;

    @FXML
    private Button btn_ajouter;
    List<User> users;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vboxinfo.setVisible(false);
        vboxfiltre.setVisible(false);
        ServiceUser service = new ServiceUser();

        users = new ArrayList<>(service.getAll());
        for (int i = 0; i < users.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/User/user_item.fxml"));
            try {
                HBox hBox = fxmlLoader.load();

                UseritemController useritem = fxmlLoader.getController();

                useritem.setData(users.get(i));

                int finalI = i;// a comprendre

                Button suppButton = (Button) hBox.lookup("#supp"); // La fonction lookup(String selector) est une méthode de la classe Node qui cherche par id dans l' hiérarchie

                if (suppButton != null) {
                    suppButton.setOnAction(event -> {
                        ServiceUser serviceUser = new ServiceUser();
                        serviceUser.supprimerByEmail(users.get(finalI).getEmail());
                        Userlayout.getChildren().remove(hBox);
                    });

                }


                hBox.setOnMouseClicked(event -> {
                    btn_modif.setVisible(true);
                    btn_bloq.setVisible(true);
                    btn_supp.setVisible(true);
                    btn_ajouter.setVisible(false);
                    User user = new User();
                    ServiceUser serviceUser = new ServiceUser();

                    display(serviceUser.findParEmail(users.get(finalI).getEmail()));


                });

                HBox.setMargin(hBox, new Insets(70, 0, 0, 0));

                Userlayout.getChildren().add(hBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }


    private void fadeIn(TextField node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), node);

        fadeTransition.setFromValue(0);

        fadeTransition.setToValue(1);

        fadeTransition.play();

        node.setVisible(true);
    }

    private void fadeOut(TextField node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), node);

        fadeTransition.setFromValue(1);

        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished(event -> node.setVisible(false));

        fadeTransition.play();
    }


    @FXML
    public void onclick(ActionEvent actionEvent) {

        showDetails();
    }

    private void showDetails() {


        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/User/Details.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);

            scene.getRoot().setStyle("-fx-padding: 5px;");

            scene.getStylesheets().add(String.valueOf(getClass().getResource("/style/styleDetails.css")));

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), root);

            fadeIn.setFromValue(0.0);

            fadeIn.setToValue(1.0);

            fadeIn.play();

            Stage dialogStage = new Stage();

            dialogStage.setScene(scene);

            dialogStage.setTitle("Détails");

            dialogStage.initModality(Modality.APPLICATION_MODAL);

            dialogStage.initStyle(StageStyle.UNDECORATED);

            DetailsController detailsController = loader.getController();

            detailsController.setScene(scene);

            dialogStage.showAndWait();
        } catch (Exception e) {

            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'affichage de la boîte de dialogue.");
            alert.showAndWait();
        }
    }

    public void btnfiltre(MouseEvent mouseEvent) {
        vboxfiltre.setVisible(!vboxfiltre.isVisible());

    }


    public void display(User user) {
        this.firstname.setText(user.getFirstname());
        this.email.setText(user.getEmail());
        this.lastname.setText(user.getLastname());
        this.age.setText(String.valueOf(user.getAge()));
        this.adresse.setText(user.getAdresse());
        this.cin.setText(user.getCin());
        this.phone.setText(String.valueOf(user.getNum()));
        this.status.setText(user.getStatus());
        this.role.setText(String.valueOf(user.getRole()));
        this.date.setValue(LocalDate.parse(user.getDate()));
        //this.dob.setValue(LocalDate.parse(user.getDob()));
        vboxinfo.setVisible(true);


        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), vboxinfo);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

    }


    @FXML
    public void btnAjouterEmploye(ActionEvent actionEvent) {
        clearTextFields(vboxinfo);
        btn_supp.setVisible(false);
        btn_bloq.setVisible(false);
        btn_modif.setVisible(false);
        vboxinfo.setVisible(true);
        btn_ajouter.setVisible(true);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), vboxinfo);
        transition.setToX(-20);
        //transition.setAutoReverse(true); // Répéter en sens inverse
        //transition.setCycleCount(TranslateTransition.INDEFINITE); // Nombre de répétitions

        transition.play();


    }


    public void ajouterEmploye(ActionEvent actionEvent) {
        Random random = new Random();
        ServiceUser serviceUser = new ServiceUser();

        User user = new User();

        user.setFirstname(firstname.getText());
        user.setLastname(lastname.getText());
        user.setEmail(email.getText());
        user.setAge(Integer.parseInt(age.getText()));
        user.setNum(Integer.parseInt(phone.getText()));
        user.setPassword(PasswordHasher.hashPassword(random.toString()));
        user.setAdresse(adresse.getText());
        user.setCin(cin.getText());
        user.setRole(Role.employe);
        user.setStatus(status.getText());
        serviceUser.ajouteremploye(user);


        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/User/user_item.fxml"));
            HBox hBox = fxmlLoader.load();
            UseritemController useritem = fxmlLoader.getController();
            useritem.setData(user);

            Userlayout.getChildren().add(hBox);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private void clearTextFields(VBox mainVBox) {
        mainVBox.getChildren().forEach(hBox -> {
            if (hBox instanceof HBox) {
                ((HBox) hBox).getChildren().forEach(vBox -> {
                    if (vBox instanceof VBox) {
                        ((VBox) vBox).getChildren().forEach(node -> {
                            if (node instanceof TextField) {
                                ((TextField) node).clear();
                            }
                        });
                    }
                });
            }
        });
    }

    public void onUsersCllik(ActionEvent actionEvent) {


        Userlayout.getChildren().clear();
        for (int i = 0; i < users.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/User/user_item.fxml"));
            HBox hBox;


            try {


                hBox = fxmlLoader.load();

            } catch (IOException e) {


                throw new RuntimeException(e);
            }


            if (users.get(i).getRole().equals(Role.simpleutlisateur)) {

                System.out.println("heloo");
                UseritemController useritem = fxmlLoader.getController();
                Userlayout.getChildren().add(hBox);
                useritem.setData(users.get(i));
            }
            int finalI = i;
            hBox.setOnMouseClicked(event -> {
                btn_modif.setVisible(true);
                btn_bloq.setVisible(true);
                btn_supp.setVisible(true);
                btn_ajouter.setVisible(false);
                User user = new User();
                ServiceUser serviceUser = new ServiceUser();

                display(serviceUser.findParEmail(users.get(finalI).getEmail()));


            });

        }


    }


    public void onEmployeeClick(ActionEvent actionEvent) {

        Userlayout.getChildren().clear();
        for (int i = 0; i < users.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/User/user_item.fxml"));
            HBox hBox;


            try {


                hBox = fxmlLoader.load();

            } catch (IOException e) {


                throw new RuntimeException(e);
            }


            if (users.get(i).getRole().equals(Role.employe)) {

                System.out.println("heloo");
                UseritemController useritem = fxmlLoader.getController();
                useritem.setData(users.get(i));
                Userlayout.getChildren().add(hBox);
            }
            int finalI = i;
            hBox.setOnMouseClicked(event -> {
                btn_modif.setVisible(true);
                btn_bloq.setVisible(true);
                btn_supp.setVisible(true);
                btn_ajouter.setVisible(false);
                User user = new User();
                ServiceUser serviceUser = new ServiceUser();

                display(serviceUser.findParEmail(users.get(finalI).getEmail()));


            });


        }

    }

    public void onAllClick(ActionEvent actionEvent) {
        Userlayout.getChildren().clear();
        for (int i = 0; i < users.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/User/user_item.fxml"));
            HBox hBox;


            try {


                hBox = fxmlLoader.load();

            } catch (IOException e) {


                throw new RuntimeException(e);
            }




                System.out.println("heloo");
                UseritemController useritem = fxmlLoader.getController();
                useritem.setData(users.get(i));
                Userlayout.getChildren().add(hBox);

            int finalI = i;
            System.out.println(i);
            hBox.setOnMouseClicked(event -> {
                btn_modif.setVisible(true);
                btn_bloq.setVisible(true);
                btn_supp.setVisible(true);
                btn_ajouter.setVisible(false);
                User user = new User();
                ServiceUser serviceUser = new ServiceUser();

                display(serviceUser.findParEmail(users.get(finalI).getEmail()));


            });


        }


    }
}
