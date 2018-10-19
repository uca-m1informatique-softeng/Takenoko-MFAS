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


public class ObjectifPandaTest {
    Partie partie = new Partie();
    Plateau plateau = partie.getPlateau();
    Bot bot = new Bot(Enums.CouleurBot.ROUGE);
    Parcelle parcelleRose = new Parcelle(Enums.TypeParcelle.ROSE);
    Panda panda = new Panda(plateau);

    @Test
    public void validationPanda() throws Exception {
        ObjectifPanda objectifpanda = new ObjectifPanda(5, Enums.TypeParcelle.ROSE,2);
        bot.addObjectif(objectifpanda);

        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,-1,0));
        plateau.poser(parcelleRose,new Point3D(0,-1,1));
        for(int i = 0 ; i < 3 ; i++){
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
            plateau.getParcelle(new Point3D(1,0,-1)).pousserBambou();
            plateau.getParcelle(new Point3D(1,-1,0)).pousserBambou();
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        bot.joueurDeplacePanda(panda);
        assertFalse(objectifpanda.validation(partie,bot));

        bot.joueurDeplacePanda(panda);
        assertTrue(objectifpanda.validation(partie,bot));
    }

}