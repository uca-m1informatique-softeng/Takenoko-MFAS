package moteur.objectifs;

import joueur.Bot;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.personnages.Panda;
import moteur.Plateau;
import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La classe test des objectifs panda
 */
public class ObjectifPandaTest {
    Partie partie=new Partie();
    Plateau pla = partie.getPlateau();
    Bot j = new Bot(Enums.CouleurBot.ROUGE);
    Parcelle par = new Parcelle(Enums.TypeParcelle.ROSE);
    Panda p = new Panda(pla);

    @Test
    public void validation() throws Exception {
        ObjectifPanda ob = new ObjectifPanda(5, Enums.TypeParcelle.ROSE,2);

        /*On crée notre plateau composé que de parcelles roses*/

        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));
        pla.poser(par,new Point3D(1,-1,0));
        pla.poser(par,new Point3D(0,-1,1));

        /*On donne un objectif au panda */
        j.addObjectif(ob);

        /*on fait pousser du bambou de taille 3 sur les parcelle */
        for(int i = 0 ; i < 3 ; i++){
            pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
            pla.getParcelle(new Point3D(1,0,-1)).pousserBambou();
            pla.getParcelle(new Point3D(1,-1,0)).pousserBambou();
            pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        /*Deplacer le panda sur une case ce qui augmentera le nombre de bambou que le joueur possède (pas assez pour réaliser l'objectif)*/
        j.joueurDeplacePanda(p);

        assertFalse(ob.validation(partie,j));
        /*objectif réalisé*/
        j.joueurDeplacePanda(p);

        assertTrue(ob.validation(partie,j));

    }

}