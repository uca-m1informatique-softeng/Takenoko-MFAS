package Joueur;
import Moteur.Parcelle;
import Moteur.Plateau;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BotRandom extends Bot{
    /**
     * Le constructeur
     *
     * @param couleur
     */
    public BotRandom(String couleur) {
        super(couleur);
    }

    public void joueurPose(Plateau plateau){
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle p = piocherParcelle();
        int tailleListe = list.size();
        if(tailleListe > 0){
            Random random = new Random();
            int r = random.nextInt(tailleListe);
            Point3D point = list.get(r);
            plateau.poser(p, point);
            System.out.println("( " + point.getX() + ", " + point.getY() + ", " + point.getZ() + ") tuile posé par le joueur " + getCouleur());

        }
    }

    public void joueurDeplaceJardinier(Plateau plateau){
        ArrayList<Point3D> listdeplacementJardinier=plateau.DestinationsPossiblesJardinier();
        int tailleListe = listdeplacementJardinier.size();
        if(tailleListe > 0){
            Random random = new Random();
            int r = random.nextInt(tailleListe);
            Point3D pointJaridnier = listdeplacementJardinier.get(r);
            plateau.DeplacerJardinier(pointJaridnier);
            System.out.println("le joueur " + getCouleur() + " a deplacé le jardinier en (" + pointJaridnier.getX() + ", " + pointJaridnier.getY() + ", " + pointJaridnier.getZ() + ")");
            System.out.println("Il y a " + plateau.getParcelle(pointJaridnier).getNbBambou() + " bambou.");
        }
    }

}
