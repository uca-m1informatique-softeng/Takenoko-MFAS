package Moteur.Objectifs;

import Moteur.Bambou;
import Moteur.Partie;
import Joueur.Joueur;

import java.util.ArrayList;
import Moteur.Enums.TypeParcelle;

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

    public boolean validation(Partie P,Joueur J){
        ArrayList<Bambou> listeBambou=J.getListBambou();
        int cmt = 0;
        for(int i = 0; i < listeBambou.size(); i++){
            if(listeBambou.get(i).getCouleur() == couleur){
                cmt++;
            }
        }
        if(cmt >= nombreBambou){
            //il faudra suppr les bambous
            setValide(true);
            return true;
        }
        return false;
    }

    public String toString() {
        return "manger "+nombreBambou+" bambou(s) "+couleur;
    }
}
