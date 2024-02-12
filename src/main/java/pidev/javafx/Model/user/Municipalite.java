package pidev.javafx.Model.user;

public class Municipalite {
    int id;
    String name;
    String adresse;

    public Municipalite() {
    }

    public Municipalite(String name, String adresse) {
        this.name = name;
        this.adresse = adresse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
