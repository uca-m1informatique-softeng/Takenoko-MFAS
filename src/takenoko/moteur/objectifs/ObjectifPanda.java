package takenoko.moteur.objectifs;

import takenoko.moteur.Bambou;
import takenoko.joueur.Joueur;
import takenoko.moteur.Enums;

import java.util.ArrayList;

/**
 * La classe des objectifs du panda
 */
public class ObjectifPanda extends Objectif{

    int nombreBambou;

    public ObjectifPanda(int valeur, Enums.TypeParcelle couleur, int nombreBambou) {
        super(couleur, valeur);
        this.nombreBambou = nombreBambou;
    }


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
    public boolean validation(Joueur joueur){
        ArrayList<Bambou> listeBambou=joueur.getListBambou();
        int cmt = 0;
        for(int i = 0; i < listeBambou.size(); i++){
            if(listeBambou.get(i).getCouleur() == this.getCouleur()){
                cmt++;
            }
        }
        if(cmt >= nombreBambou){
            joueur.supprBambou(this.getCouleur(),nombreBambou);
            setValide(true);
            return true;
        }
	setValide(false);
        return false;
    }

    public String toString() {
        return "manger "+nombreBambou+" bambou(s) "+this.getCouleur();
    }
}
