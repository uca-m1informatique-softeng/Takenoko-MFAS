package Moteur;

import javafx.geometry.Point3D;

/**
 * C'est la classe du panda
 */
public class Panda {
    private Point3D coord;


    public Panda(Point3D coord) {
        this.coord = coord;
    }

    public Panda(){
        this.coord = new Point3D(0.0,0.0,0.0);

    }

    public Point3D getCoord() {
        return coord;
    }

    public void setCoord(Point3D coord) {
        this.coord = coord;
    }
}
