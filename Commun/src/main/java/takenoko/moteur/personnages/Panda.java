package takenoko.moteur.personnages;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import takenoko.moteur.Affichage;
import takenoko.moteur.Plateau;
import javafx.geometry.Point3D;

/**
 * C'est la classe du panda
 */
@Component
@Scope("singleton")
public class Panda extends Personnage {

    private static Panda instance=null;

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
     * Action du panda lorsque il arrive a une coordonne
     * @param p
     * @return
     */
    public boolean faireActionBambou(Point3D p) {
        Plateau plateau=Plateau.getInstance();
        Affichage.affichagePanda(p,plateau);
        return plateau.getParcelle(p).mangerBambou();
    }
}
