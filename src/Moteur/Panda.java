package Moteur;

import javafx.geometry.Point3D;

/**
 * C'est la classe du panda
 */
public class Panda extends Personnage {

    public Panda(Plateau p){
        super(p);
    }

    public boolean PousserOuMangerBambou(Point3D p) {
        return this.getPlateau().getParcelle(p).mangerBambou();
    }
}
