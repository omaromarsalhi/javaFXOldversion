package pidev.javafx.Model.MarketPlace;

import java.sql.Timestamp;

public class Service extends Product {
    public Service(int id, int idUser, String name,String descreption, String imgSource, Float price, Float quantity, Boolean state, Timestamp timestamp) {
        super( id, idUser, name,descreption, imgSource, price, quantity, state, timestamp );
    }
}
