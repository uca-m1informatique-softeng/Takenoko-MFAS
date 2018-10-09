package Moteur;

import Moteur.Enums.TypeParcelle;
/**
 * C'est la classe bambou
 */
public class Bambou {

    private TypeParcelle couleur;

    public TypeParcelle getCouleur() {
        return couleur;
    }

    public void setCouleur(TypeParcelle couleur) {
        this.couleur = couleur;
    }

    /**
     * Le constructeur
     * @param couleur
     */
    public Bambou(TypeParcelle couleur){
        this.couleur=couleur;
    }
}
