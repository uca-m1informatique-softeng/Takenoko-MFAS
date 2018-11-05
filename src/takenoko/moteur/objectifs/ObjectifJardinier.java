package takenoko.moteur.objectifs;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import takenoko.moteur.Parcelle;
import takenoko.joueur.Joueur;
import takenoko.moteur.Plateau;

import java.util.ArrayList;

/**
 * La classe des objectifs du jardinier
 */
@Component
@Scope("prototype")
public class ObjectifJardinier extends Objectif{

    private int tailleBambou;


    //////////////////////////////GETTER ET SETTER//////////////////////////////

    public int getTailleBambou() {
        return tailleBambou;
    }

    public void setTailleBambou(int tailleBambou) {
        this.tailleBambou = tailleBambou;
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
        ArrayList<Parcelle> parcellesPlateau=plateau.getAllParcelle();
        for(int i = 0; i < parcellesPlateau.size(); i++){
            Parcelle parcelleCourante=parcellesPlateau.get(i);
            if( parcelleCourante.getNbBambou() >= tailleBambou && parcelleCourante.getType() == this.getCouleur()){
                setValide(true);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "faire pousser "+tailleBambou+" bambou(s) "+this.getCouleur();
    }
}

