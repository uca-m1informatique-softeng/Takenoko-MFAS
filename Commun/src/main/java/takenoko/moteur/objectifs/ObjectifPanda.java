package takenoko.moteur.objectifs;


import org.json.JSONObject;
import takenoko.joueur.Bot;
import takenoko.moteur.Enums;

/**
 * La classe des objectifs du panda
 */

public class ObjectifPanda extends Objectif {

    int nombreBambou;


    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getNombreBambou() {
        return nombreBambou;
    }

    public void setNombreBambou(int nombreBambou) {
        this.nombreBambou = nombreBambou;
    }

    public String toString() {
        return "manger " + nombreBambou + " bambou(s) " + this.getCouleur();
    }
    //////////////////////////////Méthodes//////////////////////////////

    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     *
     * @param joueur
     * @return
     */
    @Override
    public boolean validation(Bot joueur) {


        int nbBambouJ = joueur.getNbBambouDeCouleur(this.getCouleur());

        if (nbBambouJ >= nombreBambou) {
            joueur.supprBambou(this.getCouleur(), nombreBambou);
            setValide(true);
            return true;
        }
        return false;
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("valeur", this.getValeur());
        object.put("tailleBambou", nombreBambou);
        String couleur = "";
        if (this.getCouleur() == Enums.TypeParcelle.VERTE) {
            couleur = "vert";
        }
        if (this.getCouleur() == Enums.TypeParcelle.JAUNE) {
            couleur = "jaune";
        }
        if (this.getCouleur() == Enums.TypeParcelle.ROSE) {
            couleur = "rose";
        }
        object.put("couleur", couleur);

        return object;
    }
}







