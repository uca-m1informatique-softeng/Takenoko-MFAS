package takenoko.moteur;

import takenoko.moteur.Enums.TypeParcelle;
/**
 * C'est la classe bambou
 */
public class Bambou {

    private TypeParcelle couleur;

    /**
     * Le constructeur
     * @param couleur
     */
    public Bambou(TypeParcelle couleur) {
        this.couleur = couleur;
    }

    //////////////////////////////GETTER et SETTER//////////////////////////////


    public TypeParcelle getCouleur() {
        return couleur;
    }

    public void setCouleur(TypeParcelle couleur) {
        this.couleur = couleur;
    }


}
