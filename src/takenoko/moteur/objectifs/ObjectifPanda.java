package takenoko.moteur.objectifs;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import takenoko.moteur.Bambou;
import takenoko.joueur.Joueur;

import java.util.ArrayList;

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
        return false;
    }

    public String toString() {
        return "manger "+nombreBambou+" bambou(s) "+this.getCouleur();
    }
}
