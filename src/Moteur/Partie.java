package Moteur;

public class Partie {

    private Plateau plateau;
    private Jardinier jardinier;
    private Panda panda;

    public Partie() {
        plateau=new Plateau();
        jardinier=new Jardinier(plateau);
        panda=new Panda(plateau);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Jardinier getJardinier() {
        return jardinier;
    }

    public void setJardinier(Jardinier jardinier) {
        this.jardinier = jardinier;
    }

    public Panda getPanda() {
        return panda;
    }

    public void setPanda(Panda panda) {
        this.panda = panda;
    }



}
