package pidev.javafx.Model.Transport;
import java.sql.Timestamp;
import java.util.Date;

public class Abonnement {
    private int idAboonnement;
    private String type ;
    private Timestamp timestamp;
    private Date dateDebut;
    private Date dateFin;

    public Abonnement(int idAboonnement, String type, Timestamp timestamp, Date dateDebut, Date dateFin) {
        this.idAboonnement = idAboonnement;
        this.type = type;
        this.timestamp = timestamp;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getIdAboonnement() {
        return idAboonnement;
    }

    public String getType() {
        return type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Date getDateDebut() {
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

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
