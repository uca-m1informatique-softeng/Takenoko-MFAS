package Moteur.Objectifs;

import Joueur.Bot;
import Moteur.Bambou;
import Moteur.TypeParcelle;

import java.util.ArrayList;

public class ObjectifPanda extends Objectif{
    TypeParcelle couleur;
    int nombreBambou;

    public ObjectifPanda(int valeur, TypeParcelle couleur, int nombreBambou) {
        super(valeur);
        this.couleur = couleur;
        this.nombreBambou = nombreBambou;
    }

    public TypeParcelle getCouleur() {
        return couleur;
    }

    public int getNombreBambou() {
        return nombreBambou;
    }

    public boolean validation(ArrayList<Bambou> listeBambou){
        int cmt = 0;
        for(int i = 0; i < listeBambou.size(); i++){
            if(listeBambou.get(i).getCouleur() == couleur){
                cmt++;
            }
        }
        if(cmt >= nombreBambou){
            setValide(true);
            return true;
        }
        setValide(false);
        return false;
    }

}
