package Moteur;

import Moteur.Objectifs.ObjectifJardinier;
import Moteur.Objectifs.ObjectifPanda;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La classe test des decks
 */
public class DeckTest {
    Partie partie = new Partie();
    Deck d = new Deck(partie);


    @Test
    public void initialiserDeckParcelle() throws Exception {
        Parcelle[] dp = new Parcelle[28];
        for (int i = 0;i < 11;i++){
            dp[i] = new Parcelle(Enums.TypeParcelle.Verte);
        }
        for (int i = 11;i < 18;i++){
            dp[i] = new Parcelle(Enums.TypeParcelle.Rose);
        }
        for (int i = 18;i < 27;i++){
            dp[i] = new Parcelle(Enums.TypeParcelle.Jaune);
        }
        for (int i =0 ; i<27;i++){
            assertEquals(d.getDeckParcelle()[i].getType().toString(),dp[i].getType().toString());
        }

    }


    @Test
    public void initialiserObjectifsJardinier() throws Exception {
        ObjectifJardinier [] deckjar = new ObjectifJardinier[12];
        deckjar[0] = new ObjectifJardinier(6, Enums.TypeParcelle.Jaune,4);
        deckjar[1] = new ObjectifJardinier(6, Enums.TypeParcelle.Jaune,4);
        deckjar[2] = new ObjectifJardinier(6, Enums.TypeParcelle.Jaune,4);
        deckjar[3] = new ObjectifJardinier(6, Enums.TypeParcelle.Jaune,4);

        deckjar[4] = new ObjectifJardinier(7, Enums.TypeParcelle.Rose,4);
        deckjar[5] = new ObjectifJardinier(7, Enums.TypeParcelle.Rose,4);
        deckjar[6] = new ObjectifJardinier(7, Enums.TypeParcelle.Rose,4);
        deckjar[7] = new ObjectifJardinier(7, Enums.TypeParcelle.Rose,4);

        deckjar[8] = new ObjectifJardinier(5,Enums.TypeParcelle.Verte,4);
        deckjar[9] = new ObjectifJardinier(5,Enums.TypeParcelle.Verte,4);
        deckjar[10] = new ObjectifJardinier(5,Enums.TypeParcelle.Verte,4);
        deckjar[11] = new ObjectifJardinier(5,Enums.TypeParcelle.Verte,4);


        for (int i =0 ; i<12;i++){
            assertEquals(d.getDeckObjectifsJardinier()[i].getValeur(),deckjar[i].getValeur());
            assertEquals(d.getDeckObjectifsJardinier()[i].getCouleur(),deckjar[i].getCouleur());
            assertEquals(d.getDeckObjectifsJardinier()[i].getTailleBambou(),deckjar[i].getTailleBambou());
        }
    }

    @Test
    public void initialiserObjectifsPanda() throws Exception {
        ObjectifPanda [] deckpan = new ObjectifPanda[9];
        deckpan[0] = new ObjectifPanda(4,Enums.TypeParcelle.Jaune,2);
        deckpan[1] = new ObjectifPanda(4,Enums.TypeParcelle.Jaune,2);
        deckpan[2] = new ObjectifPanda(4,Enums.TypeParcelle.Jaune,2);
        deckpan[3] = new ObjectifPanda(5,Enums.TypeParcelle.Rose,2);
        deckpan[4] = new ObjectifPanda(5,Enums.TypeParcelle.Rose,2);
        deckpan[5] = new ObjectifPanda(5,Enums.TypeParcelle.Rose,2);
        deckpan[6] = new ObjectifPanda(3,Enums.TypeParcelle.Verte,2);
        deckpan[7] = new ObjectifPanda(3,Enums.TypeParcelle.Verte,2);
        deckpan[8] = new ObjectifPanda(3,Enums.TypeParcelle.Verte,2);

        for (int i =0 ; i<9;i++){
            assertEquals(d.getDeckObjectifsPanda()[i].getValeur(),deckpan[i].getValeur());
            assertEquals(d.getDeckObjectifsPanda()[i].getCouleur(),deckpan[i].getCouleur());
            assertEquals(d.getDeckObjectifsPanda()[i].getNombreBambou(),deckpan[i].getNombreBambou());
        }
    }
}