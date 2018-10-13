package joueur;

import moteur.objectifs.ObjectifJardinier;
import moteur.Partie;
import moteur.personnages.Jardinier;
import moteur.Plateau;
import javafx.geometry.Point3D;
import moteur.Enums.CouleurBot;

import java.util.ArrayList;

/**
 * La classe du bot Jardinier
 */
public class BotJardinier extends Bot{

    /**
     * Le constructeur
     * @param s
     */
    public BotJardinier(CouleurBot s) {
        super(s);
    }

    /**
     * Une méthode qui renvois un boolean pour le choix d'action du bot
     * @param numeroActionDansLeTour
     * @param partie
     * @return
     */
    public boolean choixAction(int numeroActionDansLeTour, Partie partie){
        joueurPose(partie);
        joueurDeplaceJardinier(partie.getJardinier());
        return true;
    }

    /**
     * @param jardinier
     */
    public void joueurDeplaceJardinier(Jardinier jardinier){
        Plateau plateau=jardinier.getPlateau();
        ArrayList<Point3D> listdeplacementJardinier=jardinier.DestinationsPossibles();
        boolean pasDeplacer = true;
        Point3D pointJaridnier = new Point3D(0,0,0);
        if(!listdeplacementJardinier.isEmpty()){
            ObjectifJardinier objJard = (ObjectifJardinier) getListObjectifs().get(0);
            for (Point3D p : listdeplacementJardinier){
                if(plateau.getParcelle(p).getType() == objJard.getCouleur()){
                    pointJaridnier = p;
                    pasDeplacer = false;
                    break;
                }
            }
            if(pasDeplacer){ pointJaridnier = listdeplacementJardinier.get(0);}
            jardinier.Deplacer(pointJaridnier);
            System.out.println("Il y a " + plateau.getParcelle(pointJaridnier).getNbBambou() + " bambou sur cette case");
        }
    }

}
