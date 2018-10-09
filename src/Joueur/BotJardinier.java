package Joueur;

import Moteur.Objectifs.ObjectifJardinier;
import Moteur.Partie;
import Moteur.Personnages.Jardinier;
import Moteur.Plateau;
import javafx.geometry.Point3D;
import Moteur.Enums.CouleurBot;

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
     * @param P
     * @return
     */
    public boolean choixAction(int numeroActionDansLeTour, Partie P){
        joueurPose(P);
        joueurDeplaceJardinier(P.getJardinier());
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
        if(listdeplacementJardinier.size() > 0){
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
