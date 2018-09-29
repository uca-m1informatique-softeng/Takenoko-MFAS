package Moteur.Objectifs;

import Joueur.Bot;
import Moteur.TypeParcelle;

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

}
