package takenoko.moteur.personnages;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import takenoko.moteur.Affichage;
import takenoko.moteur.Plateau;
import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * C'est la classe du jardinier
 */
@Component
@Scope("singleton")
public class Jardinier extends Personnage {

    private static Jardinier instance=null;

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * @return
     */
    public static final Jardinier getInstance() {
        if (Jardinier.instance == null) {
            Jardinier.instance = new Jardinier();
        }
        return Jardinier.instance;
    }

    /**
     * Action du jardinier lorsqu'il arrive a une coordonnée
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
     * Donne la liste des coordonnées où le bambou doit pousser suite au déplacement du jardinier
     * @param point3D
     * @return
     */
    public ArrayList<Point3D> ouPousserBambou(Point3D point3D) {
        Plateau plateau=Plateau.getInstance();
        ArrayList<Point3D> listParcelle = plateau.getParcelleVoisineMemeCouleur(point3D);
        ArrayList<Point3D> listMemeCouleur = new ArrayList<>();
        if(plateau.getParcelle(point3D).isIrriguee()){
            listMemeCouleur.add(point3D);
        }
        for(Point3D pointMemeCouleur : listParcelle){
            if(plateau.getParcelle(pointMemeCouleur).isIrriguee()){
                listMemeCouleur.add(pointMemeCouleur);
            }
        }
        return listMemeCouleur;
    }

}
