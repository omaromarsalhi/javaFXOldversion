package pidev.javafx.Controller.User;

import pidev.javafx.Model.user.Municipalite;

import java.util.List;

public interface IserviceMunicipalite<T> {

    public void ajouterMunicipalite(T t);
    public void modifierMunicipalite(T t);
    public void supprimerMunicipalite(int id);

    public T getOneById(int id);
    public List<T> getAll();
}
