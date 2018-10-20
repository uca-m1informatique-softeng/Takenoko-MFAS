package moteur;

import moteur.objectifs.ObjectifJardinier;
import moteur.objectifs.ObjectifPanda;
import moteur.objectifs.ObjectifParcelle;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * La classe test des decks
 */
public class DeckTest {
    Partie partie = new Partie();
    Deck deck = new Deck(partie);



    @Test
    public void initialiserDeckParcelle() throws Exception {
        ArrayList<Parcelle> deckParcelles = new ArrayList<>();

        for (int i = 0;i < 11;i++){
            deckParcelles.add(new Parcelle(Enums.TypeParcelle.VERTE));
        }
        for (int i = 11;i < 18;i++){
            deckParcelles.add(new Parcelle(Enums.TypeParcelle.ROSE));
        }
        for (int i = 18;i < 27;i++){
            deckParcelles.add(new Parcelle(Enums.TypeParcelle.JAUNE));
        }
        for (int i =0 ; i<27;i++){
            assertEquals(deck.getDeckParcelles().get(i).getType().toString(),deckParcelles.get(i).getType().toString());
        }

    }


    @Test
    public void initialiserDeckMotif() throws Exception{
        ArrayList<ObjectifParcelle> deckObjectifsParcelle = new ArrayList<>();
        deckObjectifsParcelle.add(new ObjectifParcelle(3, Enums.TypeParcelle.JAUNE,0));
        deckObjectifsParcelle.add(new ObjectifParcelle(3, Enums.TypeParcelle.JAUNE,1));
        deckObjectifsParcelle.add(new ObjectifParcelle(3, Enums.TypeParcelle.JAUNE,2));
        deckObjectifsParcelle.add(new ObjectifParcelle(4, Enums.TypeParcelle.JAUNE,3));
        deckObjectifsParcelle.add(new ObjectifParcelle(2, Enums.TypeParcelle.VERTE,0));
        deckObjectifsParcelle.add(new ObjectifParcelle(2, Enums.TypeParcelle.VERTE,1));
        deckObjectifsParcelle.add(new ObjectifParcelle(2, Enums.TypeParcelle.VERTE,2));
        deckObjectifsParcelle.add(new ObjectifParcelle(3, Enums.TypeParcelle.VERTE,3));
        deckObjectifsParcelle.add(new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,0));
        deckObjectifsParcelle.add(new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,1));
        deckObjectifsParcelle.add(new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,2));
        deckObjectifsParcelle.add(new ObjectifParcelle(5, Enums.TypeParcelle.ROSE,3));
        for (int i =0 ; i<12;i++){
            assertEquals(deck.getDeckObjectifsParcelle().get(i).getValeur(),deckObjectifsParcelle.get(i).getValeur());
            assertEquals(deck.getDeckObjectifsParcelle().get(i).getCouleur(),deckObjectifsParcelle.get(i).getCouleur());
            assertEquals(deck.getDeckObjectifsParcelle().get(i).getType(),deckObjectifsParcelle.get(i).getType());
        }

    }


    @Test
    public void initialiserObjectifsJardinier() throws Exception {
        ArrayList<ObjectifJardinier> deckObjectifsJardinier= new ArrayList<>();
        deckObjectifsJardinier.add(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));

        deckObjectifsJardinier.add(new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4));

        deckObjectifsJardinier.add(new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4));


        for (int i =0 ; i<12;i++){
            assertEquals(deck.getDeckObjectifsJardinier().get(i).getValeur(),deckObjectifsJardinier.get(i).getValeur());
            assertEquals(deck.getDeckObjectifsJardinier().get(i).getCouleur(),deckObjectifsJardinier.get(i).getCouleur());
            assertEquals(deck.getDeckObjectifsJardinier().get(i).getTailleBambou(),deckObjectifsJardinier.get(i).getTailleBambou());
        }
    }

    @Test
    public void initialiserObjectifsPanda() throws Exception {
        ArrayList<ObjectifPanda> deckObjectifsPanda = new ArrayList<>();
        deckObjectifsPanda.add(new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2));
        deckObjectifsPanda.add(new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2));
        deckObjectifsPanda.add(new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2));
        deckObjectifsPanda.add(new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2));
        deckObjectifsPanda.add(new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2));
        deckObjectifsPanda.add(new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2));
        deckObjectifsPanda.add(new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2));
        deckObjectifsPanda.add(new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2));
        deckObjectifsPanda.add(new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2));

        for (int i =0 ; i<9;i++){
            assertEquals(deck.getDeckObjectifsPanda().get(i).getValeur(),deckObjectifsPanda.get(i).getValeur());
            assertEquals(deck.getDeckObjectifsPanda().get(i).getCouleur(),deckObjectifsPanda.get(i).getCouleur());
            assertEquals(deck.getDeckObjectifsPanda().get(i).getNombreBambou(),deckObjectifsPanda.get(i).getNombreBambou());
        }
    }


}