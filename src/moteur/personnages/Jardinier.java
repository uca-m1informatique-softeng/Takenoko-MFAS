package moteur.personnages;

import moteur.Plateau;
import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * C'est la classe du jardinier
 */
public class Jardinier extends Personnage {

    public Jardinier(Plateau p)
    {
        super(p);
    }

    public boolean PousserOuMangerBambou(Point3D point3D) {
        boolean reponse = false;
        System.out.println("Jardinier en " + getCoord().getX() + ", " + getCoord().getY() + ", " + getCoord().getZ()+ " (Parcelle "+getPlateau().getParcelle((point3D)).getType()+")");
        ArrayList<Point3D> pointOuPousser =  ouPousserBambou(point3D);
        System.out.print("Du bambou a poussé en : ");
        for(Point3D pt : pointOuPousser){
            if(getPlateau().getParcelle(pt).pousserBambou()){
                System.out.print("( " + pt.getX()+", " + pt.getY() + ", " + pt.getZ() + " )");
                reponse = true;
            }
        }
        System.out.println("");
        return reponse;
    }

    public ArrayList<Point3D> ouPousserBambou(Point3D point3D) {

        ArrayList<Point3D> listParcelle = getPlateau().getParcelleVoisineMemeCouleur(point3D);
        ArrayList<Point3D> listMemeCouleur = new ArrayList<>();
        if(getPlateau().getParcelle(point3D).isIrriguee()){
            listMemeCouleur.add(point3D);}

        while(listParcelle.size() > 0){
            Point3D newPoint =  listParcelle.get(0);
            if(!listMemeCouleur.contains(newPoint) && getPlateau().getParcelle(newPoint).isIrriguee()){
                listMemeCouleur.add(newPoint);
                ArrayList<Point3D> newList =  getPlateau().getParcelleVoisineMemeCouleur(newPoint);
                for(Point3D pt : newList){
                    if(!listParcelle.contains(pt)){
                        listParcelle.add(pt);
                    }
                }
            }
            listParcelle.remove(0);
        }
        return listMemeCouleur;
    }

}
