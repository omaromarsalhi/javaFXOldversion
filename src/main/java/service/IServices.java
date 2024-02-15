package service;

import pidev.javafx.entities.Voiture;
import pidev.javafx.entities.Maison;


import java.util.List;

public interface IServices<T> {
    public void ajouter(T t);
    public void modifier (T t);
    public void supprimer(int id);
    public T getoneById(int id);
    public List<Voiture> getAll();
public List<Maison> affiche();
}
