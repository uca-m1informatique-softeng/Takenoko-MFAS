package joueur;

import moteur.*;
import moteur.objectifs.ObjectifPanda;
import moteur.personnages.Jardinier;
import moteur.personnages.Panda;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import moteur.Enums.CouleurBot;

/**
 * La classe du bot Panda
 */
public class BotPanda extends Bot {

    /**
     * Le constructeur
     * @param s
     */
    public BotPanda (CouleurBot s){
        super(s);
    }

    /**
     * Une méthode qui renvois un boolean pour le choix d'action du bot
     * @param numeroActionDansLeTour
     * @param partie
     * @return
     */
    public boolean choixAction(int numeroActionDansLeTour, Partie partie){
        joueurDeplaceJardinier(partie.getJardinier());
        joueurDeplacePanda(partie.getPanda());
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
            ObjectifPanda objPanda=(ObjectifPanda)getListObjectifs().get(1);
            for (Point3D p : listdeplacementJardinier){
                if(plateau.getParcelle(p).getType() == objPanda.getCouleur()){
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

    /**
     * @param panda
     */
    public void joueurDeplacePanda(Panda panda){
        Plateau plateau=panda.getPlateau();
        ArrayList<Point3D> listdeplacementPanda = panda.DestinationsPossibles();
        boolean pasDeplacer = true;
        Point3D pointPanda = new Point3D(0,0,0);
        if(!listdeplacementPanda.isEmpty()){
            ObjectifPanda objPanda=(ObjectifPanda)getListObjectifs().get(1);
            for (Point3D p : listdeplacementPanda){
                if(plateau.getParcelle(p).getType() == objPanda.getCouleur()){
                    pointPanda = p;
                    pasDeplacer = false;
                    break;
                }
            }
            if(pasDeplacer){ pointPanda = listdeplacementPanda.get(0);}
            if(panda.Deplacer(pointPanda)){
                getListBambou().add(new Bambou(plateau.getMap().get(pointPanda).getType()));
            }
            System.out.println("Il y a " + plateau.getParcelle(pointPanda).getNbBambou() + " bambou sur cette case");
        }
    }
}


