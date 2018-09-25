package Moteur;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParcelleTest {

    Parcelle parcelle = new Parcelle(TypeParcelle.etang);

    @Test
    public void pousserBambou(){
        int tailleBambou = parcelle.getListBambou().size();

        //il n'y a pas de bambou à la création de la parcelle
        assertEquals(tailleBambou,0);

        //on fait pousser deux bambous
        parcelle.pousserBambou();
        parcelle.pousserBambou();
        tailleBambou = parcelle.getListBambou().size();
        assertEquals(tailleBambou,2);

        //on essai de faire pousser plus de 4 bambous
        parcelle.pousserBambou();
        parcelle.pousserBambou();
        parcelle.pousserBambou();
        tailleBambou = parcelle.getListBambou().size();
        assertEquals(tailleBambou,4);


    }

    @Test
    public void mangerBambou(){

        //on vérifie qu'on ne peut pas manger s'il n'y a pas de bambou
        parcelle.mangerBambou();
        int tailleBambou = parcelle.getListBambou().size();
        assertEquals(tailleBambou,0);

        //on fait pousser du bambou pour les manger plus tard
        parcelle.pousserBambou();
        parcelle.pousserBambou();

        //on vérifie si le nombre de bambou diminu correctement
        parcelle.mangerBambou();
        tailleBambou = parcelle.getListBambou().size();
        assertEquals(tailleBambou,1);

        parcelle.mangerBambou();
        tailleBambou = parcelle.getListBambou().size();
        assertEquals(tailleBambou,0);
    }

}