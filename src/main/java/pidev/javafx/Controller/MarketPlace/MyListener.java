package pidev.javafx.Controller.MarketPlace;

import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Fruit;

import java.io.IOException;

public interface MyListener {
    default void onClickListener(Bien bien){}
     default void exit(){}
}
