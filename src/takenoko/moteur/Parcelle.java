package takenoko.moteur;

import java.util.ArrayList;
import takenoko.moteur.Enums.TypeParcelle;


/**
 *C'est la classe de la parcelle
 */
public class Parcelle {
    private TypeParcelle type;
    private ArrayList<Bambou> listBambou = new ArrayList<>();

    /**
     * Le constructeur
     * @param type
     */
    public Parcelle(TypeParcelle type){
        this.type = type;
        }


    //////////////////////////////GETTER et SETTER//////////////////////////////


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
        if(listBambou.size() < 4 && type!=Enums.TypeParcelle.ETANG){
            listBambou.add(new Bambou(this.type));
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
