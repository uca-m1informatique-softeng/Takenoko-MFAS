package takenoko.moteur.personnages;

import takenoko.moteur.Affichage;
import takenoko.moteur.Plateau;
import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * C'est la classe du jardinier
 */
public class Jardinier extends Personnage {

    private static Jardinier instance=null;

    /**
     * Le constructeur
     */
    public Jardinier() {
        super();
    }

    //////////////////////////////Méthodes//////////////////////////////


    public final static Jardinier getInstance() {
        if (Jardinier.instance == null) {
            Jardinier.instance = new Jardinier();
        }
        return Jardinier.instance;
    }

    /**
     * Action du jardinier lorsque il arrive a une coordonne
     * @param point3D
     * @return
     */
    public boolean faireActionBambou(Point3D point3D) {
        Plateau plateau=Plateau.getInstance();
        boolean reponse = false;
        Affichage.affichageJardinier(point3D,plateau);
        ArrayList<Point3D> pointOuPousser =  ouPousserBambou(point3D);
        for(Point3D pt : pointOuPousser){
            if(plateau.getParcelle(pt).pousserBambou()){
                Affichage.affichageNombreBambou(plateau,pt);
                reponse = true;
            }
        }
        return reponse;
    }

    /**
     * Donne la liste des coordonne ou le bambou doit pousser suite au deplacement du jardinier
     * @param point3D
     * @return
     */
    public ArrayList<Point3D> ouPousserBambou(Point3D point3D) {
        Plateau plateau=Plateau.getInstance();
        ArrayList<Point3D> listParcelle = plateau.getParcelleVoisineMemeCouleur(point3D);
        ArrayList<Point3D> listMemeCouleur = new ArrayList<>();
        for(Point3D pointMemeCouleur : listParcelle){
                listMemeCouleur.add(pointMemeCouleur);
        }
        return listMemeCouleur;
    }

}
