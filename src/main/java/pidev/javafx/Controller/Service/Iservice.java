package pidev.javafx.Controller.Service;
import pidev.javafx.Controller.Entity.Reclamation;
import java.util.Set;
public interface Iservice <T> {
    public void ajouter(T t);
    public void modifier(T t);
    public void supprimer(int idReclamation);
    public T getOneById(int idReclamation);
    public Set<T> getAll();
}
