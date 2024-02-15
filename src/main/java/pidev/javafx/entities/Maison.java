package pidev.javafx.entities;

import java.util.Objects;

public class Maison {
    private int idmaison;
    private String nomprop;
    private String adresse;
    private int numtelprop;

    public Maison(int idmaison, String nomprop, String adresse, int numtelprop) {
        this.idmaison = idmaison;
        this.nomprop = nomprop;
        this.adresse = adresse;
        this.numtelprop = numtelprop;
    }

    public Maison() {

    }

    public int getIdmaison() {
        return idmaison;
    }

    public void setIdmaison(int idmaison) {
        this.idmaison = idmaison;
    }

    public String getNomprop() {
        return nomprop;
    }

    public void setNomprop(String nomprop) {
        this.nomprop = nomprop;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumtelprop() {
        return numtelprop;
    }

    public void setNumtelprop(int numtelprop) {
        this.numtelprop = numtelprop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maison maison = (Maison) o;
        return idmaison == maison.idmaison;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmaison);
    }

    @Override
    public String toString() {
        return "Maison{" +
                "idmaison=" + idmaison +
                ", nomprop='" + nomprop + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numtelprop='" + numtelprop + '\'' +
                '}';
    }
}