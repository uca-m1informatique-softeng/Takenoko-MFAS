package Moteur.Objectifs;

import Moteur.Partie;
import Joueur.Joueur;

/**
 * La classe des Objectifs
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

    public int getValeur() {
        return valeur;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public boolean validation(Partie P,Joueur J){
        return valide;
    }
}

