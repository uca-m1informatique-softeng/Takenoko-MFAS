package takenoko.joueur;

import javafx.geometry.Point3D;
import org.junit.Before;
import org.junit.Test;
import takenoko.moteur.*;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.objectifs.ObjectifParcelle;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Le classe test du joueur
 */
public class JoueurTest {

    Partie partie;
    Plateau plateau ;
    Parcelle parcelleJaune ;
    Joueur joueur ;
    Bambou bambouJaune ;
    ObjectifJardinier objectifJardinier;
    ObjectifPanda objectifPanda;
    ObjectifParcelle objectifParcelle;

    @Before
    public void setup(){
        this.partie = new Partie();
        this.plateau = Plateau.getInstance();
        plateau.resetPlateau();
        this.parcelleJaune = new Parcelle();
        this.parcelleJaune = new Parcelle();
        parcelleJaune.setListBambou(new ArrayList<Bambou>());
        parcelleJaune.setIrriguee(false);
        parcelleJaune.setType(Enums.TypeParcelle.JAUNE);
        this.joueur = new Joueur();
        joueur.setCouleur(Enums.CouleurBot.ROUGE);
        this.bambouJaune = new Bambou();
        bambouJaune.setCouleur(Enums.TypeParcelle.JAUNE);
        this.objectifJardinier = new ObjectifJardinier();
        objectifJardinier.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifJardinier.setValeur(6);
        objectifJardinier.setTailleBambou(4);
        this.objectifPanda = new ObjectifPanda();
        objectifPanda.setValeur(4);
        objectifPanda.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifPanda.setNombreBambou(2);
        this.objectifParcelle = new ObjectifParcelle();
        objectifParcelle.setType(0);
        objectifParcelle.setValeur(3);
        objectifParcelle.setCouleur(Enums.TypeParcelle.JAUNE);
    }


    @Test
    public void verifierMesObjectifsJardinier(){

        joueur.addObjectif(objectifJardinier);

        plateau.poser(parcelleJaune,new Point3D(0,-1,1));
        for(int i = 0 ; i < 2 ; i++){
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        joueur.verifierMesObjectif();
        assertEquals(0,joueur.getNombreObjectifsRemplis());

        plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();

        joueur.verifierMesObjectif();
        assertEquals(1,joueur.getNombreObjectifsRemplis());
    }

    @Test
    public void verifierMesObjectifsPanda(){
        joueur.addObjectif(objectifPanda);
        joueur.getListBambou().add(bambouJaune);

        joueur.verifierMesObjectif();
        assertEquals(0,joueur.getNombreObjectifsRemplis());

        joueur.getListBambou().add(bambouJaune);

        joueur.verifierMesObjectif();
        assertEquals(1,joueur.getNombreObjectifsRemplis());
    }

    @Test
    public void verifierMesObjectifsParcelle(){
        joueur.addObjectif(objectifParcelle);

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));

        joueur.verifierMesObjectif();
        assertEquals(0,joueur.getNombreObjectifsRemplis());

        plateau.poser(parcelleJaune,new Point3D(2,-1,-1));

        joueur.verifierMesObjectif();
        assertEquals(1,joueur.getNombreObjectifsRemplis());
    }

    @Test
    public void joueurPoseUneParcelle(){
        plateau.resetPlateau();
        assertEquals(6,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(1,partie.getPlateau().getKeylist().size());

        joueur.joueurPose(plateau,parcelleJaune,new Point3D(0,1,-1));

        assertEquals(5,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(2,partie.getPlateau().getKeylist().size());
        assertEquals(partie.getPlateau().getKeylist().get(1),new Point3D(0,1,-1));
    }


    @Test
    public void joueurPoseDeuxParcelle(){
        plateau.resetPlateau();
        joueur.joueurPose(plateau, parcelleJaune,new Point3D(0,1,-1) );
        joueur.joueurPose(plateau,parcelleJaune,new Point3D(0,1,-1));
        assertEquals(partie.getPlateau().getKeylist().get(1),new Point3D(0,1,-1));
        assertEquals(partie.getPlateau().getKeylist().get(2),new Point3D(0,1,-1));
    }



    @Test
    public void joueurDeplaceJardinier(){
        partie.getJardinier().resetPersonnage();
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));

        joueur.joueurDeplaceJardinier( partie.getJardinier(),new Point3D(0,1,-1) );

        assertEquals(new Point3D(0,1,-1),partie.getJardinier().getCoord());
    }


    @Test
    public void joueurDeplacePanda(){
        joueur.resetJoueur();
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));

        joueur.joueurDeplacePanda( partie.getPanda(),new Point3D(0,1,-1) );

        assertEquals(new Point3D(0,1,-1),partie.getPanda().getCoord());

        ArrayList<Bambou> listBambouAttendu = new ArrayList<>();
        listBambouAttendu.add(bambouJaune);
        for (int i = 0; i < joueur.getListBambou().size(); i++) {
            assertEquals(listBambouAttendu.get(i).getCouleur(),joueur.getListBambou().get(i).getCouleur());

        }

    }


    @Test
    public void listActionRestantePossible(){
        joueur.resetJoueur();
        ArrayList<Enums.Action> listActionAttendu = new ArrayList<>();
        listActionAttendu.add(Enums.Action.PIOCHERPARCELLE);
        listActionAttendu.add(Enums.Action.PIOCHEROBJECTIFPANDA);
        listActionAttendu.add(Enums.Action.PIOCHEROBJECTIFPARCELLE);
        listActionAttendu.add(Enums.Action.PIOCHEROBJECTIFJARDINIER);
        ArrayList<Enums.Action> listAction = joueur.listActionRestantePossible();
        assertEquals(listAction,listActionAttendu);

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));

        listActionAttendu.add(Enums.Action.DEPLACERJARDINIER);
        listActionAttendu.add(Enums.Action.DEPLACERPANDA);
        listActionAttendu.add(Enums.Action.POSERIRRIGATION);

        listAction = joueur.listActionRestantePossible();
        assertEquals(listAction,listActionAttendu);
    }

    @Test
    public void supprimerBambou(){
        joueur.resetJoueur();
        joueur.getListBambou().add(bambouJaune);
        joueur.getListBambou().add(bambouJaune);
        assertEquals(joueur.getListBambou().size(), 2);
        joueur.supprBambou(Enums.TypeParcelle.JAUNE,2);
        assertEquals(joueur.getListBambou().size(), 0);

    }

    @Test
    public void piocherUneParcelle(){
        assertEquals(joueur.piocheUneParcelle(),null);
    }

    @Test
    public void joueurPose(){
        plateau.resetPlateau();
        assertEquals(plateau.getParcelle(new Point3D(1,0,-1)), null);
        joueur.joueurPose(plateau,parcelleJaune,new Point3D(1,0,-1));
        assertEquals(plateau.getParcelle(new Point3D(1,0,-1)), parcelleJaune);
    }


    @Test
    public void joueurPoseIrrigation(){
        plateau.resetPlateau();
        Irrigation irrigation = new Irrigation();
        assertEquals(plateau.getIrrigation(new Point3D(1,-0.5,-0.5)), null);
        joueur.joueurPoseIrrigation(plateau,irrigation,new Point3D(1,-0.5,-0.5));
        assertEquals(plateau.getIrrigation(new Point3D(1,-0.5,-0.5)), irrigation);
    }

    @Test
    public void actionPiocheObjectifJardinier(){
        joueur.resetJoueur();
        assertEquals(joueur.getListObjectifs().size(), 3);
        joueur.actionPiocheObjectifJardinier();
        assertEquals(joueur.getListObjectifs().size(), 4);

    }

    @Test
    public void actionPiocheObjectifPanda(){
        joueur.resetJoueur();
        assertEquals(joueur.getListObjectifs().size(), 3);
        joueur.actionPiocheObjectifPanda();
        assertEquals(joueur.getListObjectifs().size(), 4);

    }

    @Test
    public void actionPiocheObjectifParcelle(){
        joueur.resetJoueur();
        assertEquals(joueur.getListObjectifs().size(), 3);
        joueur.actionPiocheObjectifParcelle();
        assertEquals(joueur.getListObjectifs().size(), 4);

    }


}