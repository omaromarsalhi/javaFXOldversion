package pidev.javafx.entities;

import java.util.Objects;

public class Voiture {
    private int idvehicule;
    private String marque;

    private String modele;
    private String immatriculation;
    private String energie;

    private String circulation;

    public Voiture(int idvehicule, String marque, String modele, String immatriculation, String energie, String circulation) {
        this.idvehicule = idvehicule;
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.energie = energie;
        this.circulation = circulation;
    }

    public Voiture() {

    }

    public int getIdvehicule() {
        return idvehicule;
    }


    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public String getCirculation() {
        return circulation;
    }

    public void setCirculation(String circulation) {
        this.circulation = circulation;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "idvehicule=" + idvehicule +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", energie='" + energie + '\'' +
                ", circulation='" + circulation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voiture voiture = (Voiture) o;
        return idvehicule == voiture.idvehicule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idvehicule);
    }
}
