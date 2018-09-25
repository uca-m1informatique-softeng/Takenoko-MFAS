package Moteur;

import java.util.ArrayList;

public class Parcelle {
    final TypeParcelle type; //Pour le moment il y a que etang
    private boolean irriguee;
    private ArrayList<Bambou> listBambou = new ArrayList<>();

    public Parcelle(TypeParcelle type){
        this.type = type;
        if(this.type  == TypeParcelle.etang ){
            this.irriguee = true;
        }
        else{
            this.irriguee = false;
        }
    }

    public int getNbBambou(){
        return listBambou.size();
    }

    public void pousserBambou(){
        if(listBambou.size() < 4){
            listBambou.add(new Bambou());
        }
    }

    public void mangerBambou(){
        int i = listBambou.size();
        if(i > 0){
            listBambou.remove(i);
        }
    }

    @Override
    public String toString() {
        return "parcelle ok";
    }
}
