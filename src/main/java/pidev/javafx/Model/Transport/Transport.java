package pidev.javafx.Model.Transport;

public class Transport {
private int idTransport;
private String type_vehicule;
private int num_ligne;
private String depart;
private  String arivee;
private String Vehicule_Image;

    public Transport(int idTransport, String type_vehicule, int num_ligne, String depart, String arivee,String Vehicule_Image) {
        this.idTransport = idTransport;
        this.type_vehicule = type_vehicule;
        this.num_ligne = num_ligne;
        this.depart = depart;
        this.arivee = arivee;
        this.Vehicule_Image=Vehicule_Image;
    }

    public void setIdTransport(int idTransport) {
        this.idTransport = idTransport;
    }

    public void setType_vehicule(String type_vehicule) {
        type_vehicule = type_vehicule;
    }

    public void setNum_ligne(int num_ligne) {
        this.num_ligne = num_ligne;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setArivee(String arivee) {
        this.arivee = arivee;
    }

    public String getVehicule_Image() {
        return Vehicule_Image;
    }

    public void setVehicule_Image(String vehicule_Image) {
        Vehicule_Image = vehicule_Image;
    }

    public int getIdTransport() {
        return idTransport;
    }

    public String getType_vehicule() {
        return type_vehicule;
    }

    public int getNum_ligne() {
        return num_ligne;
    }

    public String getDepart() {
        return depart;
    }

    public String getArivee() {
        return arivee;
    }
}
