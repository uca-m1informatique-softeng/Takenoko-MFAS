package Moteur;

import javafx.geometry.Point3D;

/**
 * C'est la classe du jardinier
 */
public class Jardinier extends Personnage {

    private Point3D coord;

    public Jardinier(Point3D coord){
        super(coord);
    }

    public Jardinier()
    {
        super();
    }
}
