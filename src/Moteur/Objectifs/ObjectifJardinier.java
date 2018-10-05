package Moteur.Objectifs;

import Moteur.Parcelle;
import Moteur.TypeParcelle;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectifJardinier extends Objectif{

    TypeParcelle couleur;
    int tailleBambou;

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

    public boolean validation(HashMap<Point3D, Parcelle> map, ArrayList<Point3D> keyList){
        for(int i = 0; i < map.size(); i++){
            if( map.get( keyList.get(i) ).getNbBambou() == tailleBambou && map.get( keyList.get(i) ).getType() == couleur){
                setValide(true);
                return true;
            }
        }
        setValide(false);
        return false;
    }
}

