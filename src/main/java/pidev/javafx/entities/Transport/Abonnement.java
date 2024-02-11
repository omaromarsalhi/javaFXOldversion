package pidev.javafx.entities.Transport;
import java.sql.Timestamp;
import java.util.Date;

public class Abonnement {
    private int idAboonnement;
    private String type ;
    private Timestamp dateDebut;
    private Date dateFin;
    private String Nom;

    public Abonnement() {

    }

    @Override
    public String toString() {
        return "Abonnement{" +
                "idAboonnement=" + idAboonnement +
                ", type='" + type + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                '}';
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    private  String Prenom;

    public Abonnement(int idAboonnement, String type, Timestamp  dateDebut, Date dateFin) {
        this.idAboonnement = idAboonnement;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getIdAboonnement() {
        return idAboonnement;
    }

    public String getType() {
        return type;
    }



    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }


    public void setIdAboonnement(int idAboonnement) {
        this.idAboonnement = idAboonnement;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
