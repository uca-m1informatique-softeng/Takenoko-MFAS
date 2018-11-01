package moteur.objectifs;

import moteur.Enums;
import moteur.Partie;
import joueur.Joueur;

/**
 * La classe des objectifs
 */
public abstract class  Objectif {
    private final Enums.TypeParcelle couleur;
    private int valeur;
    private boolean valide;


    /**
     * Le constructeur
     * @param valeur
     */
    public Objectif(int valeur,Enums.TypeParcelle couleur){
        this.valeur = valeur;
        this.valide = false;
        this.couleur= couleur;
    }

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getValeur() {
        return valeur;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public Enums.TypeParcelle getCouleur() {
        return couleur;
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * @return
     */
    public boolean isValide() {
        return valide;
    }


    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     * @param joueur
     * @return
     */
    public boolean validation(Joueur joueur){
        return valide;
    }
}

