package Moteur.Objectifs;

import Joueur.Bot;
import Moteur.Enums;
import Moteur.Parcelle;
import Moteur.Partie;
import Moteur.Personnages.Panda;
import Moteur.Plateau;
import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectifPandaTest {
    Partie partie=new Partie();
    Plateau pla = partie.getPlateau();
    Bot j = new Bot(Enums.CouleurBot.Rouge);
    Parcelle par = new Parcelle(Enums.TypeParcelle.Rose);
    Panda p = new Panda(pla);

    @Test
    public void validation() throws Exception {
        ObjectifPanda ob = new ObjectifPanda(5, Enums.TypeParcelle.Rose,2);

        /*On crée notre plateau composé que de parcelles roses*/

        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));
        pla.poser(par,new Point3D(1,-1,0));
        pla.poser(par,new Point3D(0,-1,1));

        /*On donne un objectif au panda */
        j.AddObjectif(ob);

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