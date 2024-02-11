package pidev.javafx.Services;

import pidev.javafx.entities.Transport.Transport;

import java.util.Set;

public interface IServices <T>{
    void ajouter(T t);
    void modifier (T t);
    void supprimer(int id);
    void  getById(int id);
    public Set<Transport> getAll();
}
