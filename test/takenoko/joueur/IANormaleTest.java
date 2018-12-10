package takenoko.joueur;

import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;
import takenoko.moteur.*;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.personnages.Jardinier;
import takenoko.moteur.personnages.Panda;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * La classe test IANormale
 */
public class IANormaleTest {
    Plateau plateau = Plateau.getInstance() ;
    IANormale ia = new IANormale(Enums.CouleurBot.ROUGE);
    ObjectifJardinier objectifJardinierJ = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);
    ObjectifJardinier objectifJardinierR = new ObjectifJardinier(7,Enums.TypeParcelle.ROSE,4);
    ObjectifJardinier objectifJardinierV = new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4);
    ObjectifPanda getObjectifPandaJ = new ObjectifPanda(4, Enums.TypeParcelle.JAUNE,2);
    ObjectifPanda getObjectifPandaR = new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2);
    ObjectifPanda getObjectifPandaV = new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2);
    Parcelle parcelleJ = new Parcelle(Enums.TypeParcelle.JAUNE);
    Parcelle parcelleR = new Parcelle(Enums.TypeParcelle.ROSE);
    Parcelle parcelleV = new Parcelle(Enums.TypeParcelle.VERTE);



    @Test
    public void possedeObjectifPanda() {
        ia.addObjectif(objectifJardinierJ);
        assertEquals(null,ia.possedeObjectifPanda());
        ia.addObjectif(getObjectifPandaJ);
        assertEquals(ia.possedeObjectifPanda(),getObjectifPandaJ);
    }

    @Test
    public void couleurParcelleDestination() {
        ia.addObjectif(objectifJardinierJ);
        ia.addObjectif(objectifJardinierJ);
        ia.addObjectif(objectifJardinierR);
        ia.addObjectif(objectifJardinierV);
        assertEquals(ia.couleurParcelleDestination(0), Enums.TypeParcelle.JAUNE);
        ia.addObjectif(objectifJardinierR);
        ia.addObjectif(objectifJardinierR);
        assertEquals(ia.couleurParcelleDestination(0), Enums.TypeParcelle.ROSE);
        ia.addObjectif(objectifJardinierV);
        ia.addObjectif(objectifJardinierV);
        ia.addObjectif(objectifJardinierV);
        assertEquals(ia.couleurParcelleDestination(0), Enums.TypeParcelle.VERTE);

    }

    @Test
    public void choixParcellePioche() {
        ArrayList<Parcelle> possiblités = new ArrayList<>() ;
        possiblités.add(parcelleJ);
        possiblités.add(parcelleJ);
        possiblités.add(parcelleR);
        ia.addObjectif(objectifJardinierJ);
        ia.addObjectif(objectifJardinierR);
        assertEquals(ia.choixParcellePioche(possiblités),parcelleR);

        possiblités.clear();
        ia.addObjectif(objectifJardinierJ);
        possiblités.add(parcelleV);
        possiblités.add(parcelleR);
        possiblités.add(parcelleJ);
        assertEquals(ia.choixParcellePioche(possiblités),parcelleJ);

        possiblités.clear();
        ia.getListObjectifs().clear();
        ia.addObjectif(objectifJardinierJ);
        possiblités.add(parcelleV);
        possiblités.add(parcelleR);
        possiblités.add(parcelleR);
        assertEquals(ia.choixParcellePioche(possiblités),parcelleV);
    }

    @Test
    public void choixCoordonnePoseParcelle() {
        plateau.resetPlateau();
        plateau.poser(parcelleJ,new Point3D(1,0,-1));
        plateau.poser(parcelleV,new Point3D(1,-1,0));
        plateau.poser(parcelleR,new Point3D(0,-1,1));

        ArrayList<Point3D> possiblités = plateau.emplacementsAutorise();
        assertEquals(ia.choixCoordonnePoseParcelle(possiblités,parcelleJ),new Point3D(0,1,-1));

        plateau.poser(parcelleJ,new Point3D(0,1,-1));
        possiblités.clear();
        possiblités = plateau.emplacementsAutorise();
        assertEquals(ia.choixCoordonnePoseParcelle(possiblités,parcelleJ),new Point3D(1,1,-2));
    }

    @Test
    public void choixDeplacementJardinier() {
        plateau.resetPlateau();
        ia.addObjectif(objectifJardinierJ);
        plateau.poser(parcelleV,new Point3D(1,-1,0));
        plateau.poser(parcelleV,new Point3D(0,-1,1));
        plateau.poser(parcelleR,new Point3D(2,-1,-1));
        assertEquals(ia.choixDeplacementJardinier(Jardinier.getInstance().destinationsPossibles()),new Point3D(1,-1,0));

        plateau.poser(parcelleJ,new Point3D(1,0,-1));
        assertEquals(ia.choixDeplacementJardinier(Jardinier.getInstance().destinationsPossibles()),new Point3D(1,0,-1));

        ia.addObjectif(objectifJardinierR);
        assertEquals(ia.choixDeplacementJardinier(Jardinier.getInstance().destinationsPossibles()),new Point3D(1,0,-1));
    }

    @Test
    public void couleurSurPlateau() {
        plateau.resetPlateau();
        plateau.poser(parcelleJ,new Point3D(1,0,-1));
        plateau.poser(parcelleV,new Point3D(1,-1,0));
        plateau.poser(parcelleR,new Point3D(0,-1,1));

        assertTrue(ia.couleurSurPlateau(Enums.TypeParcelle.ROSE));

        Panda.getInstance().deplacer(new Point3D(0,-1,1));
        assertFalse(ia.couleurSurPlateau(Enums.TypeParcelle.ROSE));
    }

    @Test
    public void choixDeplacementPanda() {
        plateau.resetPlateau();
        ia.addObjectif(objectifJardinierR);
        plateau.poser(parcelleV,new Point3D(0,-1,1));
        plateau.poser(parcelleV,new Point3D(-1,0,1));
        plateau.poser(parcelleV,new Point3D(-1,1,0));
        plateau.poser(parcelleR,new Point3D(0,1,-1));

        Panda.getInstance().setCoord(new Point3D(0,-1,1));
        assertEquals(ia.choixDeplacementPanda(Panda.getInstance().destinationsPossibles()),new Point3D(0,1,-1));
    }

    @Test
    public void choixDeplacementPanda2() {
        plateau.resetPlateau();
        ia.addObjectif(objectifJardinierR);
        plateau.poser(parcelleV,new Point3D(0,-1,1));
        plateau.poser(parcelleV,new Point3D(-1,0,1));
        plateau.poser(parcelleR,new Point3D(-1,1,0));
        plateau.poser(parcelleV,new Point3D(0,1,-1));

        Panda.getInstance().setCoord(new Point3D(0,-1,1));
        assertEquals(ia.choixDeplacementPanda(Panda.getInstance().destinationsPossibles()),new Point3D(0,1,-1));
    }



    @Test
    public void ciblePanda() {
        plateau.resetPlateau();
        plateau.poser(parcelleJ,new Point3D(0,1,-1));
        plateau.poser(parcelleJ,new Point3D(1,0,-1));
        plateau.poser(parcelleV,new Point3D(1,-1,0));
        plateau.poser(parcelleR,new Point3D(0,-1,1));

        assertEquals(ia.ciblePanda(plateau.getKeylist(), Enums.TypeParcelle.JAUNE),new Point3D(0,1,-1));
    }

    @Test
    public void rechercheParcelle() {
        plateau.resetPlateau();
        plateau.poser(parcelleJ,new Point3D(1,0,-1));
        plateau.poser(parcelleV,new Point3D(1,-1,0));
        plateau.poser(parcelleR,new Point3D(2,-1,-1));
        ia.addObjectif(getObjectifPandaV);
        assertEquals(ia.rechercheParcelle(),new Point3D(1,-1,0));
        ia.addObjectif(getObjectifPandaR);
        assertEquals(ia.rechercheParcelle(),new Point3D(2,-1,-1));

    }

    @Test
    public void parcelleCommune() {
        plateau.resetPlateau();
        Point3D dest =null;
        plateau.poser(parcelleJ,new Point3D(1,0,-1));
        plateau.poser(parcelleV,new Point3D(1,-1,0));
        plateau.poser(parcelleV,new Point3D(0,-1,1));
        plateau.poser(parcelleR,new Point3D(2,-1,-1));
        ia.addObjectif(getObjectifPandaR);
        ArrayList<Point3D> destination = Panda.getInstance().destinationsPossibles();
        Panda.getInstance().setCoord(new Point3D(2,-1,-1));
        ArrayList<Point3D> newdetination = Panda.getInstance().destinationsPossibles();
        Panda.getInstance().setCoord(new Point3D(0,0,0));

        for (int i = 0; i < newdetination.size(); i++) {
            if(destination.contains(newdetination.get(i))){
                dest = newdetination.get(i);
                break;
            }
        }
        assertEquals(ia.parcelleCommune(),dest);
    }


//    @Test
//    public void premierTest(){
//        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();
//
//        BotJardinier botJardinier = new BotJardinier(Enums.CouleurBot.VERT);
//        //IANormale iaNormale = new IANormale(Enums.CouleurBot.BLEU);
//        //IANormale iaNormale2 = new IANormale(CouleurBot.ROUGE);
//
//        //BotRandom botRandom = new BotRandom(CouleurBot.ROUGE);
//        //BotPanda botPanda= new BotPanda(Enums.CouleurBot.BLEU);
//        ia.setIperm(71);
//        ia.setIperm2(21);
//
//        listeDesJoueurs.add(ia);
//        listeDesJoueurs.add(botPanda);
//
//        for (int j = 0; j < 5; j++) {
//            if (j == 0) {
//                System.out.println("------- SANS  JARDINIER ------- ");
//                ia.setJardinierBool(false);
//            }
//            if(j==1){
//                System.out.println("------- SANS PANDA ------- ");
//                ia.setJardinierBool(true);
//                ia.setPandaBool(false);
//
//            }
//            if(j==2){
//                System.out.println("------- SANS PIOCHE ------- ");
//                ia.setPandaBool(true);
//                ia.setParcellepioche(false);
//
//            }
//            if(j==3){
//                System.out.println("------- SANS POSE ------- ");
//                ia.setParcellepioche(true);
//                ia.setParcellepose(false);
//            }
//            if (j==4){
//                System.out.println("------- BASE ------- ");
//                ia.setParcellepose(true);
//            }
//            ia.setNbVictoire(0);
//            botPanda.setNbVictoire(0);
//            Affichage.setVerbose(false);
//            for (int i=0;i<1000;i++) {
//                Partie partie = new Partie();
//                partie.jouer(listeDesJoueurs);
//            }
//
//            Affichage.affichageResultatsPartie(listeDesJoueurs);
//
//
//        }
//
//    }

    public void Test (Joueur joueur,int x ,int y) {
        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        ia.setIperm(x);
        ia.setIperm2(y);

        listeDesJoueurs.add(ia);
        listeDesJoueurs.add(joueur);

        for (int j = 0; j < 5; j++) {
            if (j == 0) {
                System.out.println("------- SANS  JARDINIER ------- ");
                ia.setJardinierBool(false);
            }
            if (j == 1) {
                System.out.println("------- SANS PANDA ------- ");
                ia.setJardinierBool(true);
                ia.setPandaBool(false);

            }
            if (j == 2) {
                System.out.println("------- SANS PIOCHE ------- ");
                ia.setPandaBool(true);
                ia.setParcellepioche(false);

            }
            if (j == 3) {
                System.out.println("------- SANS POSE ------- ");
                ia.setParcellepioche(true);
                ia.setParcellepose(false);
            }
            if (j == 4) {
                System.out.println("------- BASE ------- ");
                ia.setParcellepose(true);
            }
            ia.setNbVictoire(0);
            joueur.setNbVictoire(0);
            Affichage.setVerbose(false);
            for (int i = 0; i < 1000; i++) {
                Partie partie = new Partie();
                partie.jouer(listeDesJoueurs);
            }

            Affichage.affichageResultatsPartie(listeDesJoueurs);

        }
    }

    @Test
    public void TestJ(){
        BotRandom botRandom = new BotRandom(Enums.CouleurBot.BLEU);
        BotPanda botPanda= new BotPanda(Enums.CouleurBot.BLEU);
        BotJardinier botJardinier = new BotJardinier(Enums.CouleurBot.BLEU);
        IANormale iaNormale = new IANormale(Enums.CouleurBot.BLEU);

        ///// Random /////
        System.out.println("********** vs Bot Random **********");
        Test(botRandom,119,23);
        System.out.println();

        ///// Jar /////
        System.out.println("********** vs Bot Jardinier **********");
        Test(botJardinier,119,22);

        System.out.println();
        ///// Pan /////
        System.out.println("********** vs Bot Panda **********");
        Test(botPanda,71,21);

        System.out.println();
        ///// Ia /////
        System.out.println("********** vs Ia **********");
        Test(iaNormale,116,4);
    }

/*
    @Test
    public void permutationsChoixTypeAction() {
        BotJardinier botJardinier = new BotJardinier(Enums.CouleurBot.VERT);
        IANormale iaNormale2 = new IANormale(Enums.CouleurBot.VERT);
        BotPanda botPanda= new BotPanda(Enums.CouleurBot.VERT);
        BotRandom botRandom = new BotRandom(Enums.CouleurBot.BLEU);

        permutations(botRandom); // ratio victoires : 1000 res1 = 119 , res2 23
        permutations(botJardinier);// ratio victoires : 1000 res1 = 119 , res2 22
        permutations(botPanda); //ratio victoires : 986 res1 = 71 , res2 21
        permutations(iaNormale2);  //ratio victoires : 326 res1 = 116 , res2 4
    }


    public void permutations(Joueur joueur) {
        plateau.resetPlateau();
        int nbVictoires = 0;
        int res1 = -1;
        int res2 = -1;

        for (int i = 0; i < 120; i++) {
            ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();
            listeDesJoueurs.add(joueur);
            listeDesJoueurs.add(ia);
            Affichage.setVerbose(false);
            for (int k = 0; k < 24; k++) {
                ia.setNbVictoire(0);
                joueur.setNbVictoire(0);

                for (int j = 0; j < 1000; j++) {
                    Partie partie = new Partie();
                    ia.setIperm(i);
                    ia.setIperm2(k);
                    partie.jouer(listeDesJoueurs);
                }
                if (ia.getNbVictoire() - joueur.getNbVictoire() >= nbVictoires) {
                    nbVictoires = ia.getNbVictoire() - joueur.getNbVictoire();
                    res1 = i;
                    res2 = k;
                }
            }

        }
        System.out.println("ratio victoires : " + nbVictoires + " res1 = " + res1 + " , res2 " + res2);
    }
*/

}