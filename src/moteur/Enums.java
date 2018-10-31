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

    /**
     * C'est l'énumération des differentes types d'action.
     */
    public enum Action {
        PIOCHERPARCELLE,PIOCHEROBJECTIFPANDA,PIOCHEROBJECTIFPARCELLE,PIOCHEROBJECTIFJARDINIER,DEPLACERJARDINIER,DEPLACERPANDA,POSERIRRIGATION;
    }

    /**
     * La couleur des differents bots
     */
    public enum CouleurBot {
        ROUGE,BLEU,VERT;
    }

}
