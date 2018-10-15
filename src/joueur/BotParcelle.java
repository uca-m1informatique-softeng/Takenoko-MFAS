package joueur;

import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import javafx.geometry.Point3D;
import moteur.Enums.CouleurBot;

import java.util.ArrayList;

/**
 * La classe du bot Jardinier
 */
public class BotParcelle extends Bot{

    /**
     * Le constructeur
     * @param couleur
     */
    public BotParcelle(CouleurBot couleur) {
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
     * @param partie
     */
    public void joueurPose(Partie partie){
        finirObjectif(partie);
    }

    public boolean finirObjectif(Partie partie){

        Plateau plateau=partie.getPlateau();
        ArrayList<Point3D> emplacementslibres=plateau.emplacementsAutorise();

        Parcelle parcelle = partie.getDeck().piocherParcelle();

        for (Point3D pointCourant : emplacementslibres) {
            for (int j = 0; j < 6; j++) {
                if(plateau.parcelleSuivanteLibre(pointCourant,j)) {
                    plateau.poser(parcelle, pointCourant);
                    return true;
                }
            }
        }
        //si pas fini
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        if(!list.isEmpty()){
            Point3D point = list.get(0);
            plateau.poser(parcelle, point);
        }
        return false;
    }

}
