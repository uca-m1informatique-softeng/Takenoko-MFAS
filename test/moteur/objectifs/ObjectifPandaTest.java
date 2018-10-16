package moteur.objectifs;

import joueur.Bot;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.personnages.Panda;
import moteur.Plateau;
import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La classe test des objectifs panda
 */
public class ObjectifPandaTest {
    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Bot bot = new Bot(Enums.CouleurBot.ROUGE);
    Parcelle parcelleRose = new Parcelle(Enums.TypeParcelle.ROSE);
    Panda panda = new Panda(plateau);

    @Test
    public void validation() throws Exception {
        ObjectifPanda objectifpanda = new ObjectifPanda(5, Enums.TypeParcelle.ROSE,2);

        /*On crée notre plateau composé que de parcelles roses*/

        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,-1,0));
        plateau.poser(parcelleRose,new Point3D(0,-1,1));

        /*On donne un objectif au panda */
        bot.addObjectif(objectifpanda);

        /*on fait pousser du bambou de taille 3 sur les parcelle */
        for(int i = 0 ; i < 3 ; i++){
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
            plateau.getParcelle(new Point3D(1,0,-1)).pousserBambou();
            plateau.getParcelle(new Point3D(1,-1,0)).pousserBambou();
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        /*Deplacer le panda sur une case ce qui augmentera le nombre de bambou que le joueur possède (pas assez pour réaliser l'objectif)*/
        bot.joueurDeplacePanda(panda);

        assertFalse(objectifpanda.validation(partie,bot));
        /*objectif réalisé*/
        bot.joueurDeplacePanda(panda);

        assertTrue(objectifpanda.validation(partie,bot));

    }

}