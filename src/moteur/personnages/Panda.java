package moteur.personnages;

import moteur.Plateau;
import javafx.geometry.Point3D;

/**
 * C'est la classe du panda
 */
public class Panda extends Personnage {

    public Panda(Plateau p){
        super(p);
    }

    public boolean PousserOuMangerBambou(Point3D p) {
        System.out.println("Panda en " + getCoord().getX() + ", " + getCoord().getY() + ", " + getCoord().getZ() + " (Parcelle "+getPlateau().getParcelle((p)).getType()+")");
        return this.getPlateau().getParcelle(p).mangerBambou();
    }
}
