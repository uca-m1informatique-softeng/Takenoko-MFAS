package joueur;

import moteur.*;
import moteur.Enums.CouleurBot;

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
     * Une méthode qui renvois un boolean pour le choix d'action du bot
     * @param numeroActionDansLeTour
     * @param partie
     * @return
     */
    public boolean choixAction(int numeroActionDansLeTour,Partie partie){
        joueurPose(partie);
        joueurDeplaceJardinier(partie.getJardinier());
        return true;
    }
}
