package Moteur;

import java.util.ArrayList;

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

    /**
     * La methode qui permet de faire pousser le bambou
     */
    public void pousserBambou(){
        if(listBambou.size() < 4){
            listBambou.add(new Bambou(this.type));
        }
    }

    /**
     * La methode qui permet d'enlever du bambou
     */
    public void mangerBambou(){
        int i = listBambou.size();
        if(i > 0){
            listBambou.remove(0);
        }
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
