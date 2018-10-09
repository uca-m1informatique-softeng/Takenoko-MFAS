package Moteur.Objectifs;

import Moteur.Parcelle;
import Moteur.Partie;
import Joueur.Joueur;
import Moteur.Enums.TypeParcelle;
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
     * @param P
     * @param J
     * @return
     */
    public boolean validation(Partie P,Joueur J){
        ArrayList<Parcelle> ParcellesPlateau=P.getPlateau().getAllParcelle();
        for(int i = 0; i < ParcellesPlateau.size(); i++){
            Parcelle parcelleCourante=ParcellesPlateau.get(i);
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

