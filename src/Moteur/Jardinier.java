package Moteur;

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

    public void PousserOuMangerBambou(Point3D p)
    {
        this.getPlateau().getParcelle(p).pousserBambou();
    }

}
