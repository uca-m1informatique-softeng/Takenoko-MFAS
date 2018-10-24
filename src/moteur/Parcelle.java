package moteur;

import java.util.ArrayList;
import moteur.Enums.TypeParcelle;

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

        if(this.type  == TypeParcelle.ETANG ){
            this.irriguee = true;
        }
        else{
            this.irriguee = false;
        }
    }

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

    //////////////////////////////Méthodes//////////////////////////////


    /**
     * La methode qui permet de faire pousser le bambou
     */
    public boolean pousserBambou(){
        if(listBambou.size() < 4 && isIrriguee()){
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

    @Override
    public String toString() {
        return "parcelle ok";
    }


}
