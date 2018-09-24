import javafx.geometry.Point3D;

import java.util.ArrayList;

public class Bot {

    String couleur;

    public Bot(String couleur){
        this.couleur = couleur;
    }

    private Parcelle piocherParcelle(){
        return new Parcelle(TypeParcelle.etang);
    }

    public void play(Plateau plateau, Point3D coord){ // tester plus tard que coord correspond bien à une parcelle
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle p = piocherParcelle();
        if(list.size() > 0){
            Point3D point = list.get(0);
            plateau.poser(p, point);
            System.out.println("( " + point.getX() + ", " + point.getY() + ", " + point.getZ() + ") tuile posé par le joueur " + couleur);
        }
    }

}
