package moteur.objectifs;

import moteur.Parcelle;
import joueur.Joueur;
import moteur.Enums.TypeParcelle;
import moteur.Plateau;

import java.util.ArrayList;

/**
 * La classe des objectifs du jardinier
 */
public class ObjectifJardinier extends Objectif{

    int tailleBambou;

    /**
     * Le constructeur
     * @param valeur
     * @param couleur
     * @param tailleBambou
     */
    public ObjectifJardinier(int valeur, TypeParcelle couleur, int tailleBambou) {
        super(valeur,couleur);
        this.tailleBambou = tailleBambou;
    }

    //////////////////////////////GETTER ET SETTER//////////////////////////////

    public int getTailleBambou() {
        return tailleBambou;
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

