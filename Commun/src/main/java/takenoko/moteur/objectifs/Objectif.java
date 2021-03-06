package takenoko.moteur.objectifs;


import org.json.JSONObject;
import takenoko.joueur.Bot;
import takenoko.moteur.Enums;

/**
 * La classe des objectifs
 */

public abstract class Objectif {

    private Enums.TypeParcelle couleur;
    private int valeur;

    private boolean valide = false;

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getValeur() {
        return valeur;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public Enums.TypeParcelle getCouleur() {
        return couleur;
    }

    public void setCouleur(Enums.TypeParcelle couleur) {
        this.couleur = couleur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public boolean isValide() {
        return valide;
    }
    //////////////////////////////Méthodes//////////////////////////////



    /**
     * C'est la méthode qui renvois un True quand l'objectif est réalisé.
     * @param joueur
     * @return
     */
    public boolean validation(Bot joueur){
        return valide;
    }

    public Enums.TypeParcelle FromJson(JSONObject obj){
        if (obj.getString("couleur")=="vert"){
            return Enums.TypeParcelle.VERTE;
        }
        if (obj.getString("couleur")=="jaune"){
            return Enums.TypeParcelle.JAUNE;
        }
        if (obj.getString("couleur")=="rose"){
            return Enums.TypeParcelle.ROSE;
        }

        return Enums.TypeParcelle.ETANG;

    }

}

