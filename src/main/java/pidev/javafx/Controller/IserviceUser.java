package pidev.javafx.Controller;

import pidev.javafx.Model.user.User;

import java.util.List;

public interface IserviceUser<T> {
    public void ajouterUser(T t);
    public void ajouteremploye(T t);

    void ajouterResponsable(User user);

    public void modifier(T t);
    public void supprimer(int id);
    public void supprimerByEmail(String email);
    public T getOneById(int id);
    public List<User> getAll();
    public boolean chercherParEmail(String email);
}
