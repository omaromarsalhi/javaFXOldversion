package pidev.javafx.test;

import service.Voitureservice;

public class Main{



    public static void main(String[] args) {
        Voitureservice vs = new Voitureservice();
      /*vs.ajouter(new Voiture(2,"renualt","clio","159tounes2178","diesel","166000km"));*/
        /*vs.supprimer(2);*/
      /*vs.modifier(new Voiture(1,"mercedes","benz","139tounes2153","diesel","136000lm"));*/
        System.out.println(vs.getAll());
       /*Maisonsservice ms = new Maisonsservice();*/
      /* ms.ajouter(new Maison(1,"ghassen","nabeulImrookais",54966019));*/
       /*ms.modifier(new Maison(1,"ghassen","nabeulImrookais",54967019));*/
        /*System.out.println(ms.affiche());*/
    }


}