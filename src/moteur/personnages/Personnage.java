package moteur.personnages;

import moteur.Plateau;
import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * C'est la classe du panda
 */
public abstract class Personnage {
    private Point3D coord;


    public Personnage(Point3D coord) {
        this.coord = coord;
    }

    public Personnage(){
        this.coord = new Point3D(0.0,0.0,0.0);
    }

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public Point3D getCoord() {
        return coord;
    }

    public void setCoord(Point3D coord) {
        this.coord = coord;
    }

    //////////////////////////////Méthodes//////////////////////////////

    public void resetPersonnage(){
        this.coord = new Point3D(0.0,0.0,0.0);
    }

    /**
     * Renvoyer la liste des deplacement possibles pour un personnage
     * @return
     */
    public ArrayList<Point3D> destinationsPossibles() {
        Plateau plateau=Plateau.getInstance();
        ArrayList<Point3D> listvoisin = plateau.getParcelleVoisine(new Point3D(0,0,0));
        ArrayList<Point3D> resultat=new ArrayList<>();

        for (int i=0;i<6;i++) {
            Point3D direction=listvoisin.get(i);
            Point3D jardinier=this.getCoord();
            Point3D temp= new Point3D(jardinier.getX()+direction.getX(),jardinier.getY()+direction.getY(),jardinier.getZ()+direction.getZ());
            while(plateau.isParcelleOccupee(temp)){
                resultat.add(temp);
                double tempX=temp.getX();
                double tempY=temp.getY();
                double tempZ=temp.getZ();
                temp= new Point3D(tempX+direction.getX(),tempY+direction.getY(),tempZ+direction.getZ());
            }
        }
        return resultat;
    }


    /**
     * La méthode qui permet de déplacer le personnage sur le plateau
     * @param p
     */
    //pour le deplacement (pas de verification)
    public boolean deplacer(Point3D p){
        this.setCoord(p);
        return faireActionBambou(p);
    }

    /**
     * @param p
     * @return
     */
    public boolean faireActionBambou(Point3D p) {
        //a modidifier dans les fils
        return false;
    }
}