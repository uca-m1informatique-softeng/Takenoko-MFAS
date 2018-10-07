package Moteur;

import java.util.ArrayList;
import Moteur.Enums.TypeParcelle;

/**
 *C'est la classe de la parcelle
 */
public class Parcelle {
    final TypeParcelle type; //Pour le moment il y a que etang
    private boolean irriguee;
    private ArrayList<Bambou> listBambou = new ArrayList<>();

    /**
     * Le constructeur
     * @param type
     */
    public Parcelle(TypeParcelle type){
        this.type = type;
        this.irriguee = true;
        /*if(this.type  == TypeParcelle.etang ){
            this.irriguee = true;
        }
        else{
            this.irriguee = false;
        }*/
    }


    public int getNbBambou(){
        return listBambou.size();
    }

    public TypeParcelle getType() { return type; }

    /**
     * La methode qui permet de faire pousser le bambou
     */
    public boolean pousserBambou(){
        if(listBambou.size() < 4){
            listBambou.add(new Bambou(this.type));
            return true;
        }
        return false;
    }

    /**
     * La methode qui permet d'enlever du bambou
     */
    public boolean mangerBambou(){
        int i = listBambou.size();
        if(i > 0){
            listBambou.remove(0);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "parcelle ok";
    }

    public ArrayList<Bambou> getListBambou() {
        return listBambou;
    }

    public void setListBambou(ArrayList<Bambou> listBambou) {
        this.listBambou = listBambou;
    }
}
