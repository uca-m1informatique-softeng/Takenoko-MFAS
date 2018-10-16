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
     * @param couleur
     */
    public BotJardinier(CouleurBot couleur) {
        super(couleur);
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
     * C'est la méthode qui permet au BotJardinier de déplacer le jardinier
     * @param jardinier
     */
    public void joueurDeplaceJardinier(Jardinier jardinier){
        Plateau plateau=jardinier.getPlateau();
        ArrayList<Point3D> listdeplacementJardinier=jardinier.DestinationsPossibles();
        boolean pasDeplacer = true;
        Point3D pointJaridnier = new Point3D(0,0,0);
        if(!listdeplacementJardinier.isEmpty()){
            ObjectifJardinier objJard = (ObjectifJardinier) getListObjectifs().get(0);
            for (Point3D coordonne : listdeplacementJardinier){
                if(plateau.getParcelle(coordonne).getType() == objJard.getCouleur()){
                    pointJaridnier = coordonne;
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
