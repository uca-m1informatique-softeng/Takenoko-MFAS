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




}
