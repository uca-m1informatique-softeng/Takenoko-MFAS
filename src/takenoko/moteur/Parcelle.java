package takenoko.moteur;

import java.util.ArrayList;
import takenoko.moteur.Enums.TypeParcelle;


/**
 *C'est la classe de la parcelle
 */
public class Parcelle {
    private TypeParcelle type;
    private boolean irriguee;
    private ArrayList<Bambou> listBambou = new ArrayList<>();

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
        return listBambou.size();
    }

    public TypeParcelle getType() { return type; }


    public ArrayList<Bambou> getListBambou() {
        return listBambou;
    }

    public void setListBambou(ArrayList<Bambou> listBambou) {
        this.listBambou = listBambou;
    }

    public void setType(TypeParcelle type) {
        this.type = type;
    }

    //////////////////////////////Méthodes//////////////////////////////


    /**
     * La methode qui permet de faire pousser le bambou
     */
    public boolean pousserBambou(){
        if(listBambou.size() < 4 && isIrriguee() && type!=Enums.TypeParcelle.ETANG){
            Bambou bambou = new Bambou();
            bambou.setCouleur(this.type);
            listBambou.add(bambou);
            return true;
        }
        return false;
    }

    /**
     * La methode qui permet d'enlever du bambou
     */
    public boolean mangerBambou(){
        int nombreBambou = listBambou.size();
        if(nombreBambou > 0){
            listBambou.remove(0);
            return true;
        }
        return false;
    }

}
