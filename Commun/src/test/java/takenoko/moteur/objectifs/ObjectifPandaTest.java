package takenoko.moteur.objectifs;

import org.junit.Before;
import org.junit.Test;
import takenoko.joueur.Bot;
import takenoko.moteur.Enums;

import static org.junit.Assert.*;

/**
 * La classe test des ObjectifPanda
 */
public class ObjectifPandaTest {
    Bot bot ;
    ObjectifPanda objectifPanda;

    @Before
    public void setup(){
        this.bot = new Bot();
        bot.setCouleur(Enums.CouleurBot.ROUGE);
        this.objectifPanda = new ObjectifPanda();
        objectifPanda.setValeur(5);
        objectifPanda.setCouleur(Enums.TypeParcelle.ROSE);
        objectifPanda.setNombreBambou(2);
        bot.resetJoueur();
    }

    @Test
    public void validationObjectifPanda() throws Exception {

        bot.addObjectif(objectifPanda);
        bot.addNbBambouDeCouleur(Enums.TypeParcelle.ROSE,1);
        assertFalse(objectifPanda.validation(bot));

        bot.addNbBambouDeCouleur(Enums.TypeParcelle.ROSE,1);
        assertTrue(objectifPanda.validation(bot));

        assertEquals(0,bot.getNbBambouDeCouleur(Enums.TypeParcelle.ROSE));

    }

}