package moteur.objectifs;

import moteur.Bambou;
import moteur.Partie;
import joueur.Joueur;

import java.util.ArrayList;
import moteur.Enums.TypeParcelle;

/**
 * La classe des objectifs du panda
 */
public class ObjectifPanda extends Objectif{
    TypeParcelle couleur;
    int nombreBambou;

    /**
     * Le constructeur
     * @param valeur
     * @param couleur
     * @param nombreBambou
     */
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

    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     * @param partie
     * @param joueur
     * @return
     */
    public boolean validation(Partie partie,Joueur joueur){
        ArrayList<Bambou> listeBambou=joueur.getListBambou();
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
