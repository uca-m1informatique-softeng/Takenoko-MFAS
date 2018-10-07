package Moteur;

public class Enums {

    /**
     *C'est l'énumeration des differentes types de parcelle
     */
    public enum TypeParcelle {
        etang,Rose,Jaune,Verte;
    }

    public enum Action {
        PiocherParcelle,PiocherObjectifPanda,PiocherObjectifJardinier,DeplacerJardinier,DeplacerPanda;
    }

    public enum CouleurBot {
        Rouge,Bleu,Vert;
    }

}
