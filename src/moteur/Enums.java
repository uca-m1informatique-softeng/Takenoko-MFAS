package moteur;

/**
 * La classe qui regroupe tout nos enumérations
 */
public class Enums {

    /**
     *C'est l'énumeration des differentes types de parcelle
     */
    public enum TypeParcelle {
        ETANG,ROSE,JAUNE,VERTE;
    }

    public enum Action {
        PIOCHERPARCELLE,PIOCHEROBJECTIFPANDA,PIOCHEROBJECTIFJARDINIER,DEPLACERJARDINIER,DEPLACERPANDA;
    }

    public enum CouleurBot {
        ROUGE,BLEU,VERT;
    }

}
