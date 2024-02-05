package pidev.javafx.Controller.MarketPlace;

import pidev.javafx.Model.MarketPlace.Bien;

public interface MyListener {
    default void onClickListener(Bien bien){}
     default void exit(){}
}
