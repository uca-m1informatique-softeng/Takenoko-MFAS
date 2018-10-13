package moteur.objectifs;

import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import javafx.geometry.Point3D;


import java.awt.*;
import java.util.ArrayList;

public class ObjectifParcelle extends Objectif {

    private final Enums.TypeParcelle couleur;
    private int tailleBambou;
    private int type; //0 pour droit ; 1 pour courbé //2 pour triangle //3 pour 4 parcelles

    /**
     * Le constructeur
     *
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
                return "3 parcelles alignées";
            case 1:
                return "3 parcelles courbées";
            case 2:
                return "3 parcelles en triangle";
            case 3:
                return "4 parcelles";
            default:
                return "motif incorrect";
        }
    }
}