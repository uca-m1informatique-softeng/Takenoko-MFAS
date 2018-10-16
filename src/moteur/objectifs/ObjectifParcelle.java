package moteur.objectifs;

import joueur.Joueur;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import javafx.geometry.Point3D;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe des objectifs parcelle
 */
public class ObjectifParcelle extends Objectif {

    private final Enums.TypeParcelle couleur;
    private int tailleBambou;
    private int type; //0 pour droit ; 1 pour courbé //2 pour triangle //3 pour 4 parcelles

    /**
     * Le constructeur
     * @param valeur
     * @param couleur
     * @param type
     */
    public ObjectifParcelle(int valeur, Enums.TypeParcelle couleur, int type) {
        super(valeur);
        this.couleur = couleur;
        this.type = type;
    }

    public Enums.TypeParcelle getCouleur() {
        return couleur;
    }

    public int getType() {
        return type;
    }

    public String toString() {
        switch (type) {
            case 0:
                return "3 parcelles alignées " + couleur;
            case 1:
                return "3 parcelles courbées " + couleur;
            case 2:
                return "3 parcelles en triangle " + couleur;
            case 3:
                return "4 parcelles " + couleur;
            default:
                return "motif incorrect";
        }
    }

    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     * @param partie
     * @param J
     * @return
     */
    public boolean validation(Partie partie, Joueur J){
        Plateau plateau = partie.getPlateau();
        ArrayList<Point3D> pointsPlateau = plateau.getKeylist();

        for(int i = 0; i < pointsPlateau.size(); i++) { //on parcours la map
            Point3D pointCourant = pointsPlateau.get(i);

            if(plateau.chercheMotifParcelle(pointCourant, couleur, type)){
                return true;
            }
        }
        return false;
    }
}