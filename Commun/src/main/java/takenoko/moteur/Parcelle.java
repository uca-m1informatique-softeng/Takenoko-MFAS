package takenoko.moteur;

import org.json.JSONObject;
import takenoko.moteur.Enums.TypeParcelle;


/**
 *C'est la classe de la parcelle
 */
public class Parcelle {
    private TypeParcelle type;
    private boolean irriguee;
    private int nbBambou=0;

    //////////////////////////////GETTER et SETTER//////////////////////////////


    /**
     * @return
     */
    public boolean isIrriguee() {
        return irriguee;
    }

    public void setIrriguee(boolean irriguee) {
        this.irriguee = irriguee;
    }

    public int getNbBambou(){
        return nbBambou;
    }

    public void setNbBambou(int nb){
        nbBambou=nb;
    }

    public TypeParcelle getType() { return type; }

    public void setType(TypeParcelle type) {
        this.type = type;
    }

    //////////////////////////////Méthodes//////////////////////////////


    /**
     * La methode qui permet de faire pousser le bambou
     */
    public boolean pousserBambou(){
        if(nbBambou < 4 && isIrriguee() && type!=Enums.TypeParcelle.ETANG){
            nbBambou++;
            return true;
        }
        return false;
    }

    /**
     * La methode qui permet d'enlever du bambou
     */
    public boolean mangerBambou(){
        int nombreBambou = nbBambou;
        if(nombreBambou > 0){
            nbBambou--;
            return true;
        }
        return false;
    }


    /////////// JSON ////////////

    public JSONObject toJson(){
        JSONObject parcelle = new JSONObject();
        String couleur = "";
        if (this.getType() == TypeParcelle.VERTE){
            couleur = "vert";
        }
        if (this.getType() == TypeParcelle.JAUNE){
            couleur = "jaune";
        }
        if (this.getType() == TypeParcelle.ROSE){
            couleur = "rose";
        }
        if (this.getType() == TypeParcelle.ETANG){
            couleur = "etang";
        }

        parcelle.put("couleur",couleur);
        return parcelle;
    }

    public Enums.TypeParcelle FromJson(JSONObject obj){
        if (obj.getString("couleur")=="vert"){
            return TypeParcelle.VERTE;
        }
        if (obj.getString("couleur")=="jaune"){
            return TypeParcelle.JAUNE;
        }
        if (obj.getString("couleur")=="rose"){
            return TypeParcelle.ROSE;
        }

        return TypeParcelle.ETANG;

    }

}
