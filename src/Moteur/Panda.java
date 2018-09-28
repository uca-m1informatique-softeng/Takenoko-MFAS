package Moteur;

import javafx.geometry.Point3D;

/**
 * C'est la classe du panda
 */
public class Panda extends Personnage {

    public Panda(Plateau p){
        super(p);
    }

    public void PousserOuMangerBambou(Point3D p)
    {
        this.getPlateau().getParcelle(p).mangerBambou();
    }
}
