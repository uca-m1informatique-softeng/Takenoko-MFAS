package moteur.personnages;

import moteur.Affichage;
import moteur.Plateau;
import javafx.geometry.Point3D;

/**
 * C'est la classe du panda
 */
public class Panda extends Personnage {

    public Panda(Plateau p){
        super(p);
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * @param p
     * @return
     */
    public boolean faireActionBambou(Point3D p) {
        Affichage.affichagePanda(p,getPlateau());
        return this.getPlateau().getParcelle(p).mangerBambou();
    }
}
