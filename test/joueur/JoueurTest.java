package joueur;

import moteur.*;
import moteur.objectifs.ObjectifJardinier;
import javafx.geometry.Point3D;
import moteur.objectifs.ObjectifPanda;
import moteur.objectifs.ObjectifParcelle;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Le classe test du joueur
 */
public class JoueurTest {

    Partie partie=new Partie();
    Plateau plateau =partie.getPlateau();
    Parcelle parcelleJaune = new Parcelle(Enums.TypeParcelle.JAUNE);
    Joueur joueur = new Joueur(Enums.CouleurBot.ROUGE);


    @Test
    public void verifierMesObjectifsJardinier(){
        ObjectifJardinier objectifJardinier = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);
        joueur.addObjectif(objectifJardinier);

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(0,-1,1));
        for(int i = 0 ; i <3 ; i++){
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        joueur.verifierMesObjectif(partie);
        assertEquals(0,joueur.getNombreObjectifsRemplis());

        plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();

        joueur.verifierMesObjectif(partie);
        assertEquals(1,joueur.getNombreObjectifsRemplis());
    }

    @Test
    public void verifierMesObjectifsPanda(){
        ObjectifPanda objectifPanda = new ObjectifPanda(4, Enums.TypeParcelle.JAUNE,2);
        joueur.addObjectif(objectifPanda);
        joueur.getListBambou().add(new Bambou(Enums.TypeParcelle.JAUNE));

        joueur.verifierMesObjectif(partie);
        assertEquals(0,joueur.getNombreObjectifsRemplis());

        joueur.getListBambou().add(new Bambou(Enums.TypeParcelle.JAUNE));

        joueur.verifierMesObjectif(partie);
        assertEquals(1,joueur.getNombreObjectifsRemplis());
    }

    @Test
    public void verifierMesObjectifsParcelle(){
        ObjectifParcelle objectifParcelle = new ObjectifParcelle(3, Enums.TypeParcelle.JAUNE,0);
        joueur.addObjectif(objectifParcelle);

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));

        joueur.verifierMesObjectif(partie);
        assertEquals(0,joueur.getNombreObjectifsRemplis());

        plateau.poser(parcelleJaune,new Point3D(2,-1,-1));

        joueur.verifierMesObjectif(partie);
        assertEquals(1,joueur.getNombreObjectifsRemplis());
    }

    @Test
    public void joueurPoseUneParcelle(){
        assertEquals(6,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(1,partie.getPlateau().getKeylist().size());

        joueur.joueurPose(partie);

        assertEquals(5,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(2,partie.getPlateau().getKeylist().size());
        assertEquals(partie.getPlateau().getKeylist().get(1),new Point3D(0,1,-1));
    }


    @Test
    public void joueurPoseDeuxParcelle(){
        joueur.joueurPose(partie);
        joueur.joueurPose(partie);
        assertEquals(partie.getPlateau().getKeylist().get(1),new Point3D(0,1,-1));
        assertEquals(partie.getPlateau().getKeylist().get(2),new Point3D(1,0,-1));
    }



    @Test
    public void joueurDeplaceJardinier(){
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));

        joueur.joueurDeplaceJardinier(partie.getJardinier());

        assertEquals(new Point3D(0,1,-1),partie.getJardinier().getCoord());
    }


    @Test
    public void joueurDeplacePanda(){
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));

        joueur.joueurDeplacePanda(partie.getPanda());

        assertEquals(new Point3D(0,1,-1),partie.getPanda().getCoord());
    }

    @Test
    public void choixAction(){
        assertEquals(1,plateau.getKeylist().size());
        assertEquals(new Point3D(0,0,0),partie.getJardinier().getCoord());
        joueur.choixAction(1,partie);
        assertEquals(2,plateau.getKeylist().size());
        assertEquals(new Point3D(0,1,-1),partie.getJardinier().getCoord());
    }
}