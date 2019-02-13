package takenoko.moteur.objectifs;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import takenoko.joueur.Joueur;
import takenoko.moteur.Plateau;
import javafx.geometry.Point3D;


import java.util.ArrayList;

/**
 * La classe des objectifs parcelle
 */

public class ObjectifParcelle extends Objectif {

    private int type; //0 pour droit ; 1 pour courbé //2 pour triangle //3 pour 4 parcelles

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toString() {
        switch (type) {
            case 0:
                return "3 parcelles alignées " + getCouleur();
            case 1:
                return "3 parcelles courbées " + getCouleur();
            case 2:
                return "3 parcelles en triangle " + getCouleur();
            case 3:
                return "4 parcelles " + getCouleur();
            default:
                return "motif incorrect";
        }
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     * @param joueur
     * @return
     */
    @Override
    public boolean validation(Joueur joueur){
        Plateau plateau=Plateau.getInstance();
        ArrayList<Point3D> pointsPlateau = plateau.getKeylist();

        for(int i = 0; i < pointsPlateau.size(); i++) { //on parcours la map
            Point3D pointCourant = pointsPlateau.get(i);

            if(plateau.chercheMotifParcelle(pointCourant, getCouleur(), type)){
                return true;
            }
        }
        return false;
    }
}