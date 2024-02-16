package pidev.javafx.entities.Marketplace;

import java.sql.Timestamp;

public class Service extends Product {
    public Service(int id, int idUser, String name, String imgSource, Float price, Float quantity, Boolean state, Timestamp timestamp) {
        super( id, idUser, name, imgSource, price, quantity, state, timestamp );
    }
}
