package joueur;

import javafx.geometry.Point3D;
import moteur.*;
import moteur.Enums.CouleurBot;
import moteur.objectifs.Objectif;

import java.util.ArrayList;

/**
 *C'est la classe des bots
 */
public class Bot extends Joueur {

    /**
     * Le constructeur
     * @param couleur
     */
    public Bot(CouleurBot couleur){
        super(couleur);
    }

    /**
     * La méthode qui retourne les possibilités pour poser une parcelle.
     * @param possibilites
     * @param parcelle
     * @return
     */
    @Override
    public Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites, Parcelle parcelle) {
        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour poser une irrigation.
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixCoordonnePoseIrrigation(ArrayList<Point3D> possibilites) {
        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour piocher une parcelle
     * @param possibilites
     * @return
     */
    @Override
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites) {
        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour déplacer le jardinier
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites) {
        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour déplacer le panda
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        return possibilites.get(0);
    }

    /**
     * @param possibilites
     * @return
     */
    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        return possibilites.get(0);
    }

    /**
     * @return
     */
    @Override
    public Objectif choixObjectifPrioritaire() {
        return this.getListObjectifs().get(0);
    }
}
