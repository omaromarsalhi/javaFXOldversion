package pidev.javafx.Controller.Crud;

import javafx.collections.ObservableList;
import pidev.javafx.Model.MarketPlace.Product;

public interface CrudInterface<T> {

    void addItem(T variable);

    void updateItem(T variable);

    ObservableList<T> selectItems();
    T selectFirstItems();

    void deleteItem(int id);
}

