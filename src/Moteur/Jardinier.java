package Moteur;

import javafx.geometry.Point3D;

public class Jardinier {

    private Point3D coord;

    public Jardinier(Point3D coord){
        this.coord=coord;
    }

    public Jardinier()
    {
        this.coord=new Point3D(0.0,0.0,0.0);
    }


    public Point3D getCoord() {
        return coord;
    }

    public void setCoord(Point3D coord) {
        this.coord = coord;
    }
}
