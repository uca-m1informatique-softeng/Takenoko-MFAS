package takenoko.moteur.objectifs;

import takenoko.moteur.Enums;
import takenoko.moteur.Parcelle;
import takenoko.joueur.Joueur;
import takenoko.moteur.Plateau;

import java.util.ArrayList;

/**
 *La classe des objectifs du jardinier
 */
public class ObjectifJardinier extends Objectif{

    private int tailleBambou;

    /**
     * Le constructeur
     * @param valeur
     * @param couleur
     * @param tailleBambou
     */
    public ObjectifJardinier(int valeur, Enums.TypeParcelle couleur, int tailleBambou) {
        super(couleur, valeur);
        this.tailleBambou = tailleBambou;
    }


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
	setValide(false);
        return false;
    }

    public String toString() {
        return "faire pousser "+tailleBambou+" bambou(s) "+this.getCouleur();
    }
}

