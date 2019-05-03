package takenoko.moteur.objectifs;


import takenoko.joueur.Bot;

/**
 * La classe des objectifs du panda
 */

public class ObjectifPanda extends Objectif{

    int nombreBambou;


    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getNombreBambou() {
        return nombreBambou;
    }

    public void setNombreBambou(int nombreBambou) {
        this.nombreBambou = nombreBambou;
    }
    //////////////////////////////Méthodes//////////////////////////////

    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     * @param joueur
     * @return
     */
    @Override
    public boolean validation(Bot joueur){


        int nbBambouJ=joueur.getNbBambouDeCouleur(this.getCouleur());

        if (nbBambouJ >= nombreBambou) {
            joueur.supprBambou(this.getCouleur(),nombreBambou);
            setValide(true);
            return true;
        }
        return false;
    }

    public String toString() {
        return "manger "+nombreBambou+" bambou(s) "+this.getCouleur();
    }
}
