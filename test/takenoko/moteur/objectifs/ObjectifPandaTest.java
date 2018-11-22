/*
package takenoko.moteur.objectifs;

import org.junit.Before;
import org.junit.Test;
import takenoko.joueur.Bot;
import takenoko.moteur.Bambou;
import takenoko.moteur.Enums;

import java.util.ArrayList;

import static org.junit.Assert.*;

*/
/**
 * La classe test des ObjectifPanda
 *//*

public class ObjectifPandaTest {
    Bot bot ;
    Bambou bambou;
    ObjectifPanda objectifPanda;

    @Before
    public void setup(){
        this.bot = new Bot();
        bot.setCouleur(Enums.CouleurBot.ROUGE);
        this.bambou= new Bambou();
        bambou.setCouleur(Enums.TypeParcelle.ROSE);
        this.objectifPanda = new ObjectifPanda();
        objectifPanda.setValeur(5);
        objectifPanda.setCouleur(Enums.TypeParcelle.ROSE);
        objectifPanda.setNombreBambou(2);

    }

    @Test
    public void validationObjectifPanda() throws Exception {

        bot.addObjectif(objectifPanda);

        bot.getListBambou().add(bambou);
        assertFalse(objectifPanda.validation(bot));

        bot.getListBambou().add(bambou);
        assertTrue(objectifPanda.validation(bot));

        ArrayList<Bambou> listBambouCompare = new ArrayList<>();
        assertEquals(listBambouCompare,bot.getListBambou());

    }

}*/
