package moteur.objectifs;

import moteur.Partie;
import joueur.Joueur;

/**
 * La classe des objectifs
 */
public abstract class  Objectif {
    private int valeur;
    private boolean valide;


    /**
     * Le constructeur
     * @param valeur
     */
    public Objectif(int valeur){
        this.valeur = valeur;
        this.valide = false;
    }

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getValeur() {
        return valeur;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    //////////////////////////////Méthodes//////////////////////////////

    public boolean isValide() {
        return valide;
    }


    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     * @param partie
     * @param joueur
     * @return
     */
    public boolean validation(Partie partie,Joueur joueur){
        return valide;
    }
}

