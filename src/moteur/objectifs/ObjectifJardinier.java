package moteur.objectifs;

import moteur.Parcelle;
import moteur.Partie;
import joueur.Joueur;
import moteur.Enums.TypeParcelle;
import java.util.ArrayList;

/**
 * La classe des objectifs du jardinier
 */
public class ObjectifJardinier extends Objectif{

    TypeParcelle couleur;
    int tailleBambou;

    /**
     * Le constructeur
     * @param valeur
     * @param couleur
     * @param tailleBambou
     */
    public ObjectifJardinier(int valeur, TypeParcelle couleur, int tailleBambou) {
        super(valeur);
        this.couleur = couleur;
        this.tailleBambou = tailleBambou;
    }

    public TypeParcelle getCouleur() {
        return couleur;
    }

    public int getTailleBambou() {
        return tailleBambou;
    }

    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     * @param partie
     * @param joueur
     * @return
     */
    public boolean validation(Partie partie,Joueur joueur){
        ArrayList<Parcelle> parcellesPlateau=partie.getPlateau().getAllParcelle();
        for(int i = 0; i < parcellesPlateau.size(); i++){
            Parcelle parcelleCourante=parcellesPlateau.get(i);
            if( parcelleCourante.getNbBambou() == tailleBambou && parcelleCourante.getType() == couleur){
                setValide(true);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "faire pousser "+tailleBambou+" bambou(s) "+couleur;
    }
}

