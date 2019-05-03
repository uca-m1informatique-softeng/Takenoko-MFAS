package takenoko.moteur;

import org.junit.Test;

import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.objectifs.ObjectifParcelle;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * La classe test des decks
 */
public class DeckTest {
    Deck deck = new Deck();
    Parcelle parcelleJaune = new Parcelle();
    Parcelle parcelleRose = new Parcelle();
    Parcelle parcelleVerte = new Parcelle();


    @Test
    public void initialiserDeckParcelle() throws Exception {
        deck.initialiserDeckParcelle();

        ArrayList<Parcelle> deckParcelles = new ArrayList<>();

        for (int i = 0;i < 11;i++){
            parcelleVerte.setIrriguee(false);
            parcelleVerte.setType(Enums.TypeParcelle.VERTE);
            deckParcelles.add(parcelleVerte);
        }
        for (int i = 11;i < 18;i++){
            parcelleRose.setIrriguee(false);
            parcelleRose.setType(Enums.TypeParcelle.ROSE);
            deckParcelles.add(parcelleRose);
        }
        for (int i = 18;i < 27;i++){
            parcelleJaune.setIrriguee(false);
            parcelleJaune.setType(Enums.TypeParcelle.JAUNE);
            deckParcelles.add(parcelleJaune);
        }
        System.out.println(deckParcelles.size());
        System.out.println(deckParcelles.get(0).getType());
        for (int i =0 ; i<1;i++){
            assertEquals(deck.getDeckParcelles().get(i).getType().toString(),deckParcelles.get(i).getType().toString());
        }

    }


    @Test
    public void initialiserDeckObjectifsParcelle() throws Exception{
        deck.initialiserObjectifParcelle();

        ArrayList<ObjectifParcelle> deckObjectifsParcelle = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ObjectifParcelle objectifParcelle = new ObjectifParcelle();
            objectifParcelle.setValeur(3);
            objectifParcelle.setCouleur(Enums.TypeParcelle.JAUNE);
            objectifParcelle.setType(i);
            deckObjectifsParcelle.add(objectifParcelle);
        }

        for (int i = 0; i < 2; i++) {
            ObjectifParcelle objectifParcelle4Jaune = new ObjectifParcelle();
            objectifParcelle4Jaune.setValeur(4);
            objectifParcelle4Jaune.setCouleur(Enums.TypeParcelle.JAUNE);
            objectifParcelle4Jaune.setType(3);
            deckObjectifsParcelle.add(objectifParcelle4Jaune);
        }


        for (int i = 0; i < 3; i++) {
            ObjectifParcelle objectifParcelle = new ObjectifParcelle();
            objectifParcelle.setValeur(2);
            objectifParcelle.setCouleur(Enums.TypeParcelle.VERTE);
            objectifParcelle.setType(i);
            deckObjectifsParcelle.add(objectifParcelle);
        }

        for (int i = 0; i < 2; i++) {
            ObjectifParcelle objectifParcelle4Vert = new ObjectifParcelle();
            objectifParcelle4Vert.setValeur(3);
            objectifParcelle4Vert.setCouleur(Enums.TypeParcelle.VERTE);
            objectifParcelle4Vert.setType(3);
            deckObjectifsParcelle.add(objectifParcelle4Vert);
        }



        for (int i = 0; i < 3; i++) {
            ObjectifParcelle objectifParcelle = new ObjectifParcelle();
            objectifParcelle.setValeur(4);
            objectifParcelle.setCouleur(Enums.TypeParcelle.ROSE);
            objectifParcelle.setType(i);
            deckObjectifsParcelle.add(objectifParcelle);
        }
        for (int i = 0; i < 2; i++) {
            ObjectifParcelle objectifParcelle4Rose = new ObjectifParcelle();
            objectifParcelle4Rose.setValeur(5);
            objectifParcelle4Rose.setCouleur(Enums.TypeParcelle.ROSE);
            objectifParcelle4Rose.setType(3);
            deckObjectifsParcelle.add(objectifParcelle4Rose);
        }

        for (int i =0 ; i<15;i++){
            assertEquals(deck.getDeckObjectifsParcelle().get(i).getValeur(),deckObjectifsParcelle.get(i).getValeur());
            assertEquals(deck.getDeckObjectifsParcelle().get(i).getCouleur(),deckObjectifsParcelle.get(i).getCouleur());
            assertEquals(deck.getDeckObjectifsParcelle().get(i).getType(),deckObjectifsParcelle.get(i).getType());
        }

    }


    @Test
    public void initialiserObjectifsJardinier() throws Exception {
        deck.initialiserObjectifsJardinier();

        ArrayList<ObjectifJardinier> deckObjectifsJardinier= new ArrayList<>();

        for (int i = 0;i<5;i++){
            ObjectifJardinier objectifJardinier = new ObjectifJardinier();
            objectifJardinier.setValeur(6);
            objectifJardinier.setCouleur(Enums.TypeParcelle.JAUNE);
            objectifJardinier.setTailleBambou(4);
            deckObjectifsJardinier.add(objectifJardinier);

        }

        for (int i = 5;i<10;i++){
            ObjectifJardinier objectifJardinier = new ObjectifJardinier();
            objectifJardinier.setValeur(7);
            objectifJardinier.setCouleur(Enums.TypeParcelle.ROSE);
            objectifJardinier.setTailleBambou(4);
            deckObjectifsJardinier.add(objectifJardinier);
        }
        for (int i = 10;i<15;i++){

            ObjectifJardinier objectifJardinier = new ObjectifJardinier();
            objectifJardinier.setValeur(5);
            objectifJardinier.setCouleur(Enums.TypeParcelle.VERTE);
            objectifJardinier.setTailleBambou(4);
            deckObjectifsJardinier.add(objectifJardinier);

        }


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

        for (int i = 0;i<4;i++){
            ObjectifPanda objectifPanda = new ObjectifPanda();
            objectifPanda.setValeur(4);
            objectifPanda.setCouleur(Enums.TypeParcelle.JAUNE);
            objectifPanda.setNombreBambou(2);

            deckObjectifsPanda.add(objectifPanda);
        }

        for (int i = 4;i<7;i++){
            ObjectifPanda objectifPanda = new ObjectifPanda();
            objectifPanda.setValeur(5);
            objectifPanda.setCouleur(Enums.TypeParcelle.ROSE);
            objectifPanda.setNombreBambou(2);

            deckObjectifsPanda.add(objectifPanda);
        }
        for (int i = 7;i<12;i++){
            ObjectifPanda objectifPanda = new ObjectifPanda();
            objectifPanda.setValeur(3);
            objectifPanda.setCouleur(Enums.TypeParcelle.VERTE);
            objectifPanda.setNombreBambou(2);

            deckObjectifsPanda.add(objectifPanda);
        }

        ObjectifPanda objectifPandaJaune = new ObjectifPanda();
        objectifPandaJaune.setValeur(6);
        objectifPandaJaune.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifPandaJaune.setNombreBambou(3);
        deckObjectifsPanda.add(objectifPandaJaune);

        ObjectifPanda objectifPandaVert = new ObjectifPanda();
        objectifPandaVert.setValeur(6);
        objectifPandaVert.setCouleur(Enums.TypeParcelle.VERTE);
        objectifPandaVert.setNombreBambou(3);
        deckObjectifsPanda.add(objectifPandaVert);


        ObjectifPanda objectifPandaRose = new ObjectifPanda();
        objectifPandaRose.setValeur(6);
        objectifPandaRose.setCouleur(Enums.TypeParcelle.ROSE);
        objectifPandaRose.setNombreBambou(3);
        deckObjectifsPanda.add(objectifPandaRose);

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
        deck.initialiserDeckParcelle();
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
        deck.initialiserObjectifsJardinier();
        assertEquals(deck.getDeckObjectifsJardinier().size(),15);
        deck.piocheObjectifJardinier();
        assertEquals(deck.getDeckObjectifsJardinier().size(),14);
        deck.getDeckObjectifsJardinier().clear();
        assertEquals(null,deck.piocheObjectifJardinier());
        assertEquals(deck.getDeckObjectifsJardinier().size(),0);
    }


    @Test
    public void piocheObjectifPanda() throws Exception {
        deck.initialiserObjectifsPanda();
        assertEquals(deck.getDeckObjectifsPanda().size(),15);
        deck.piocheObjectifPanda();
        assertEquals(deck.getDeckObjectifsPanda().size(),14);
        deck.getDeckObjectifsPanda().clear();
        assertEquals(null,deck.piocheObjectifPanda());
        assertEquals(deck.getDeckObjectifsPanda().size(),0);

    }

    @Test
    public void piocheObjectifParcelle() throws Exception {
        deck.initialiserObjectifParcelle();
        assertEquals(deck.getDeckObjectifsParcelle().size(),15);
        deck.piocheObjectifParcelle();
        assertEquals(deck.getDeckObjectifsParcelle().size(),14);
        deck.getDeckObjectifsParcelle().clear();
        assertEquals(null,deck.piocheObjectifParcelle());
        assertEquals(deck.getDeckObjectifsParcelle().size(),0);
    }
}