package moteur.personnages;

import moteur.Affichage;
import moteur.Plateau;
import javafx.geometry.Point3D;

/**
 * C'est la classe du panda
 */
public class Panda extends Personnage {

    private static Panda instance=null;

    public Panda(){
        super();
    }

    //////////////////////////////Méthodes//////////////////////////////

    public final static Panda getInstance() {
        if (Panda.instance == null) {
            Panda.instance = new Panda();
        }
        return Panda.instance;
    }

    /**
     * @param p
     * @return
     */
    public boolean faireActionBambou(Point3D p) {
        Plateau plateau=Plateau.getInstance();
        Affichage.affichagePanda(p,plateau);
        return plateau.getParcelle(p).mangerBambou();
    }
}
