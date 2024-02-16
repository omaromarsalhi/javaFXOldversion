package pidev.javafx.entities.Marketplace;

import java.sql.Timestamp;

<<<<<<< HEAD:src/main/java/pidev/javafx/Model/MarketPlace/Bien.java
public class Bien extends Product{
=======
public class Bien extends Product {


>>>>>>> Aziz_Gmaty_branch:src/main/java/pidev/javafx/entities/Marketplace/Bien.java
    private Categorie categorie;

    public Bien() {
    }

    public Bien(int id, int idUser, String name, String descreption, String imgSource, Float price, Float quantity, Boolean state, Timestamp timestamp, Categorie categorie) {
        super( id, idUser, name, descreption, imgSource, price, quantity, state, timestamp, "BIEN" );
        this.categorie = categorie;
    }



    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
