package moteur;

import moteur.objectifs.ObjectifJardinier;
import moteur.objectifs.ObjectifPanda;
import moteur.objectifs.ObjectifParcelle;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La classe test des decks
 */
public class DeckTest {
    Partie partie = new Partie();
    Deck deck = new Deck(partie);



    @Test
    public void initialiserDeckParcelle() throws Exception {
        Parcelle[] deckparcelle = new Parcelle[28];
        for (int i = 0;i < 11;i++){
            deckparcelle[i] = new Parcelle(Enums.TypeParcelle.VERTE);
        }
        for (int i = 11;i < 18;i++){
            deckparcelle[i] = new Parcelle(Enums.TypeParcelle.ROSE);
        }
        for (int i = 18;i < 27;i++){
            deckparcelle[i] = new Parcelle(Enums.TypeParcelle.JAUNE);
        }
        for (int i =0 ; i<27;i++){
            assertEquals(deck.getDeckParcelle()[i].getType().toString(),deckparcelle[i].getType().toString());
        }

    }


    @Test
    public void initialiserDeckMotif() throws Exception{
        ObjectifParcelle [] deckObjectifParcelle = new ObjectifParcelle[12];
        deckObjectifParcelle[0] = new ObjectifParcelle(3, Enums.TypeParcelle.JAUNE,0);
        deckObjectifParcelle[1] = new ObjectifParcelle(3, Enums.TypeParcelle.JAUNE,1);
        deckObjectifParcelle[2] = new ObjectifParcelle(3, Enums.TypeParcelle.JAUNE,2);
        deckObjectifParcelle[3] = new ObjectifParcelle(4, Enums.TypeParcelle.JAUNE,3);
        deckObjectifParcelle[4] = new ObjectifParcelle(2, Enums.TypeParcelle.VERTE,0);
        deckObjectifParcelle[5] = new ObjectifParcelle(2, Enums.TypeParcelle.VERTE,1);
        deckObjectifParcelle[6] = new ObjectifParcelle(2, Enums.TypeParcelle.VERTE,2);
        deckObjectifParcelle[7] = new ObjectifParcelle(3, Enums.TypeParcelle.VERTE,3);
        deckObjectifParcelle[8] = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,0);
        deckObjectifParcelle[9] = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,1);
        deckObjectifParcelle[10] = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,2);
        deckObjectifParcelle[11] = new ObjectifParcelle(5, Enums.TypeParcelle.ROSE,3);
        for (int i =0 ; i<12;i++){
            assertEquals(deck.getDeckObjectifsParcelles()[i].getValeur(),deckObjectifParcelle[i].getValeur());
            assertEquals(deck.getDeckObjectifsParcelles()[i].getCouleur(),deckObjectifParcelle[i].getCouleur());
            assertEquals(deck.getDeckObjectifsParcelles()[i].getType(),deckObjectifParcelle[i].getType());
        }

    }


    @Test
    public void initialiserObjectifsJardinier() throws Exception {
        ObjectifJardinier [] deckjardinier = new ObjectifJardinier[12];
        deckjardinier[0] = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);
        deckjardinier[1] = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);
        deckjardinier[2] = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);
        deckjardinier[3] = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);

        deckjardinier[4] = new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4);
        deckjardinier[5] = new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4);
        deckjardinier[6] = new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4);
        deckjardinier[7] = new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4);

        deckjardinier[8] = new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4);
        deckjardinier[9] = new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4);
        deckjardinier[10] = new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4);
        deckjardinier[11] = new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4);


        for (int i =0 ; i<12;i++){
            assertEquals(deck.getDeckObjectifsJardinier()[i].getValeur(),deckjardinier[i].getValeur());
            assertEquals(deck.getDeckObjectifsJardinier()[i].getCouleur(),deckjardinier[i].getCouleur());
            assertEquals(deck.getDeckObjectifsJardinier()[i].getTailleBambou(),deckjardinier[i].getTailleBambou());
        }
    }

    @Test
    public void initialiserObjectifsPanda() throws Exception {
        ObjectifPanda [] deckpanda = new ObjectifPanda[9];
        deckpanda[0] = new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2);
        deckpanda[1] = new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2);
        deckpanda[2] = new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2);
        deckpanda[3] = new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2);
        deckpanda[4] = new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2);
        deckpanda[5] = new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2);
        deckpanda[6] = new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2);
        deckpanda[7] = new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2);
        deckpanda[8] = new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2);

        for (int i =0 ; i<9;i++){
            assertEquals(deck.getDeckObjectifsPanda()[i].getValeur(),deckpanda[i].getValeur());
            assertEquals(deck.getDeckObjectifsPanda()[i].getCouleur(),deckpanda[i].getCouleur());
            assertEquals(deck.getDeckObjectifsPanda()[i].getNombreBambou(),deckpanda[i].getNombreBambou());
        }
    }


}