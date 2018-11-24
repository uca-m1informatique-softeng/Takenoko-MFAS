package takenoko.moteur.personnages;

import takenoko.moteur.Affichage;
import takenoko.moteur.Plateau;
import javafx.geometry.Point3D;

/**
 * C'est la classe du panda
 */
public class Panda extends Personnage {

    private static Panda instance=null;

    public Panda() {
        super();
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * @return
     */
    public final static Panda getInstance() {
        if (Panda.instance == null) {
            Panda.instance = new Panda();
        }
        return Panda.instance;
    }

    /**
     * action du panda lorsque il arrive a une coordonne
     * @param p
     * @return
     */
    public boolean faireActionBambou(Point3D p) {
        Plateau plateau=Plateau.getInstance();
        Affichage.affichagePanda(p,plateau);
        return plateau.getParcelle(p).mangerBambou();
    }
}
