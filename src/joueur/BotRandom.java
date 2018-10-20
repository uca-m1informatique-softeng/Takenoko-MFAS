package joueur;
import moteur.*;

import moteur.Enums.CouleurBot;

/**
 * La classe du bot Random(pour le moment elle n'est plus utiliser)
 */
public class BotRandom extends Bot{

    /**
     * Le constructeur
     * @param couleur
     */
    public BotRandom (CouleurBot couleur){
        super(couleur);
    }

    /**
     * @param numeroActionDansLeTour
     * @param partie
     * @return
     */
    public boolean choixAction(int numeroActionDansLeTour,Partie partie){
        joueurPose(partie);
        joueurPoseIrrigation(partie);
        joueurDeplaceJardinier(partie.getJardinier());
        return true;
    }

}
