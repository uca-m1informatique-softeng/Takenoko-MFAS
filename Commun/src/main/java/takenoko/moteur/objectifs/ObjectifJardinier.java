package takenoko.moteur.objectifs;


import org.json.JSONObject;
import takenoko.joueur.Bot;
import takenoko.moteur.Enums;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Plateau;

import java.util.ArrayList;

/**
 * La classe des objectifs du jardinier
 */

public class ObjectifJardinier extends Objectif{

    private int tailleBambou;


    //////////////////////////////GETTER ET SETTER//////////////////////////////

    public int getTailleBambou() {
        return tailleBambou;
    }

    public void setTailleBambou(int tailleBambou) {
        this.tailleBambou = tailleBambou;
    }

    public String toString() {
        return "faire pousser "+tailleBambou+" bambou(s) "+this.getCouleur();
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     * @param joueur
     * @return
     */
    @Override
    public boolean validation(Bot joueur){
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

    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("valeur", this.getValeur());
        object.put("tailleBambou", tailleBambou);
        String couleur = "";
        if (this.getCouleur() == Enums.TypeParcelle.VERTE){
            couleur = "vert";
        }
        if (this.getCouleur() == Enums.TypeParcelle.JAUNE){
            couleur = "jaune";
        }
        if (this.getCouleur() == Enums.TypeParcelle.ROSE){
            couleur = "rose";
        }
        object.put("couleur",couleur);

        return object;
    }




}

