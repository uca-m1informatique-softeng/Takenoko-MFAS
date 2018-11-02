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
    Deck deck = new Deck();
    Parcelle parcelleJaune = new Parcelle(Enums.TypeParcelle.JAUNE);

    @Test
    public void initialiserDeckParcelle() throws Exception {
        deck.initialiserDeckParcelle();

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
    public void initialiserDeckObjectifsParcelle() throws Exception{
        deck.initialiserObjectifParcelle();

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
        deck.initialiserObjectifsJardinier();

        ArrayList<ObjectifJardinier> deckObjectifsJardinier= new ArrayList<>();
        deckObjectifsJardinier.add(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));

        deckObjectifsJardinier.add(new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4));

        deckObjectifsJardinier.add(new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4));
        deckObjectifsJardinier.add(new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4));


        for (int i =0 ; i<15;i++){
            assertEquals(deck.getDeckObjectifsJardinier().get(i).getValeur(),deckObjectifsJardinier.get(i).getValeur());
            assertEquals(deck.getDeckObjectifsJardinier().get(i).getCouleur(),deckObjectifsJardinier.get(i).getCouleur());
            assertEquals(deck.getDeckObjectifsJardinier().get(i).getTailleBambou(),deckObjectifsJardinier.get(i).getTailleBambou());
        }
    }

    @Test
    public void initialiserObjectifsPanda() throws Exception {
        deck.initialiserObjectifsPanda();

        ArrayList<ObjectifPanda> deckObjectifsPanda = new ArrayList<>();
        deckObjectifsPanda.add(new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2));
        deckObjectifsPanda.add(new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2));
        deckObjectifsPanda.add(new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2));
        deckObjectifsPanda.add(new ObjectifPanda(4,Enums.TypeParcelle.JAUNE,2));
        deckObjectifsPanda.add(new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2));
        deckObjectifsPanda.add(new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2));
        deckObjectifsPanda.add(new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2));
        deckObjectifsPanda.add(new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2));
        deckObjectifsPanda.add(new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2));
        deckObjectifsPanda.add(new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2));
        deckObjectifsPanda.add(new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2));
        deckObjectifsPanda.add(new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2));
        deckObjectifsPanda.add(new ObjectifPanda(6, Enums.TypeParcelle.JAUNE,3));
        deckObjectifsPanda.add(new ObjectifPanda(6, Enums.TypeParcelle.ROSE,3));
        deckObjectifsPanda.add(new ObjectifPanda(6, Enums.TypeParcelle.VERTE,3));

        for (int i =0 ; i<15;i++){
            assertEquals(deck.getDeckObjectifsPanda().get(i).getValeur(),deckObjectifsPanda.get(i).getValeur());
            assertEquals(deck.getDeckObjectifsPanda().get(i).getCouleur(),deckObjectifsPanda.get(i).getCouleur());
            assertEquals(deck.getDeckObjectifsPanda().get(i).getNombreBambou(),deckObjectifsPanda.get(i).getNombreBambou());
        }
    }

    @Test
    public void piocherParcelle() throws Exception {
        deck.initialiserDeckParcelle();
        deck.piocherParcelle();
        assertEquals(24,deck.getDeckParcelles().size());
        deck.piocherParcelle();
        assertEquals(21,deck.getDeckParcelles().size());
        deck.piocherParcelle();
        assertEquals(18,deck.getDeckParcelles().size());
        deck.piocherParcelle();
        assertEquals(15,deck.getDeckParcelles().size());
        deck.piocherParcelle();
        assertEquals(12,deck.getDeckParcelles().size());
        deck.piocherParcelle();
        assertEquals(9,deck.getDeckParcelles().size());
        deck.piocherParcelle();
        assertEquals(6,deck.getDeckParcelles().size());
        deck.piocherParcelle();
        assertEquals(3,deck.getDeckParcelles().size());
        deck.getDeckParcelles().remove(0);
        assertEquals(2,deck.getDeckParcelles().size());
        deck.piocherParcelle();
        assertEquals(0,deck.getDeckParcelles().size());
    }

    @Test
    public void remettreParcellesDansDeck() throws Exception {
        initialiserDeckParcelle();
        deck.piocherParcelle();

        ArrayList<Parcelle> listeARemettre = new ArrayList<>();
        deck.remettreParcellesDansDeck(listeARemettre);
        assertEquals(24,deck.getDeckParcelles().size());

        listeARemettre.add(parcelleJaune);
        deck.remettreParcellesDansDeck(listeARemettre);
        assertEquals(25,deck.getDeckParcelles().size());
        assertEquals(deck.getDeckParcelles().get(24),parcelleJaune);
    }

    @Test
    public void piocheObjectifJardinier() throws Exception {
        assertEquals(deck.getDeckObjectifsJardinier().size(),15);
        deck.piocheObjectifJardinier();
        assertEquals(deck.getDeckObjectifsJardinier().size(),14);
        deck.getDeckObjectifsJardinier().clear();
        assertEquals(null,deck.piocheObjectifJardinier());
        assertEquals(deck.getDeckObjectifsJardinier().size(),0);
    }


    @Test
    public void piocheObjectifPanda() throws Exception {
        assertEquals(deck.getDeckObjectifsPanda().size(),15);
        deck.piocheObjectifPanda();
        assertEquals(deck.getDeckObjectifsPanda().size(),14);
        deck.getDeckObjectifsPanda().clear();
        assertEquals(null,deck.piocheObjectifPanda());
        assertEquals(deck.getDeckObjectifsPanda().size(),0);

    }

    @Test
    public void piocheObjectifParcelle() throws Exception {
        assertEquals(deck.getDeckObjectifsParcelle().size(),15);
        deck.piocheObjectifParcelle();
        assertEquals(deck.getDeckObjectifsParcelle().size(),14);
        deck.getDeckObjectifsParcelle().clear();
        assertEquals(null,deck.piocheObjectifParcelle());
        assertEquals(deck.getDeckObjectifsParcelle().size(),0);

    }


}