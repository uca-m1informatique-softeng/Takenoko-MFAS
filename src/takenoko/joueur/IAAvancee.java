package takenoko.joueur;

import javafx.geometry.Point3D;
import takenoko.joueur.utilitaire.Utilitaire;
import takenoko.moteur.Commande;
import takenoko.moteur.Enums;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Plateau;
import takenoko.moteur.objectifs.Objectif;
import takenoko.moteur.personnages.Panda;

import java.util.ArrayList;
import java.util.List;

public class IAAvancee extends Bot{

    private ArrayList<Commande> listCommandes = new ArrayList<>();
    private ArrayList<Commande> listCommandes2 = new ArrayList<>();

    private List<List<Integer>> permutations, permutations2;

    private int iperm = 119; // dernier indice de permutations pour la 1ere partie des commandes

    private int iperm2 = 5; //dernier indice de permutations pour la 2eme partie des commandes

    /**
     * Le constructeur
     *
     * @param couleur
     */
    public IAAvancee(Enums.CouleurBot couleur) {
        super(couleur);
        initialiseCommandes();
    }

    public void setIperm(int iperm) {
        this.iperm = iperm;
    }

    public void setIperm2(int iperm2) {
        this.iperm2 = iperm2;
    }


    public void initialiseCommandes(){
        listCommandes.add(new Commande(Enums.Action.PIOCHEROBJECTIFPANDA));
        listCommandes.add(new Commande(Enums.Action.PIOCHEROBJECTIFJARDINIER));
        listCommandes.add(new Commande(Enums.Action.DEPLACERPANDA));
        listCommandes.add(new Commande(Enums.Action.PIOCHERPARCELLE));
        listCommandes.add(new Commande(Enums.Action.DEPLACERJARDINIER));
        permutations = Utilitaire.permutations(listCommandes.size());

        listCommandes2.add(new Commande(Enums.Action.DEPLACERPANDA));
        listCommandes2.add(new Commande(Enums.Action.DEPLACERJARDINIER));
        listCommandes2.add(new Commande(Enums.Action.PIOCHERPARCELLE));
        permutations2 = Utilitaire.permutations(listCommandes2.size());
    }


    public void actualiseCommandes(ArrayList<Enums.Action> possibilites){
        Plateau plateau=Plateau.getInstance();
        Objectif objectif = possedeObjectifPanda();
        listCommandes.get(0).setConditions(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA)
                && this.getListObjectifs().size()+getNombreObjectifsRemplis() < 9);

        listCommandes.get(1).setConditions(possibilites.contains(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)
                && (!possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA) || this.getListObjectifs().size()+getNombreObjectifsRemplis() > 7)));

        listCommandes.get(2).setConditions(possibilites.contains(Enums.Action.DEPLACERPANDA) && bambouMangeableParPanda() && objectif != null);

        listCommandes.get(3).setConditions(possibilites.contains(Enums.Action.PIOCHERPARCELLE) && !plateau.couleurSurPlateau(couleurParcelleDestination(1)));

        listCommandes.get(4).setConditions(possibilites.contains(Enums.Action.DEPLACERJARDINIER) && objectif == null);

        listCommandes2.get(0).setConditions(possibilites.contains(Enums.Action.DEPLACERPANDA));
        listCommandes2.get(1).setConditions(possibilites.contains(Enums.Action.DEPLACERJARDINIER));
        listCommandes2.get(2).setConditions(possibilites.contains(Enums.Action.PIOCHERPARCELLE));
    }

    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        Plateau plateau= Plateau.getInstance();
        actualiseCommandes(possibilites);
        //si on débute la partie
        if(plateau.getKeylist().size() == 1 && possibilites.contains(Enums.Action.PIOCHERPARCELLE)){
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(plateau.getKeylist().size() == 1 && possibilites.contains(Enums.Action.DEPLACERPANDA)){
            return Enums.Action.DEPLACERPANDA;
        }

        for (int i = 0; i < listCommandes.size(); i++) {
            if(listCommandes.get(permutations.get(iperm).get(i)).isConditions()){
                return listCommandes.get(permutations.get(iperm).get(i)).getAction();
            }
        }

        for (int i = 0; i < listCommandes2.size(); i++) {
            if(listCommandes2.get(permutations2.get(iperm2).get(i)).isConditions()){
                return listCommandes2.get(permutations2.get(iperm2).get(i)).getAction();
            }
        }

        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour déplacer le jardinier
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites){
        Enums.TypeParcelle couleur;
        if(possedeObjectifPanda() == null){
            couleur = couleurParcelleDestination(0);
        }
        else {
            couleur = couleurParcelleDestination(1);
        }

        for (int i =0;i > 4;i++){
            for (Point3D coordonne : possibilites) {
                Parcelle parcelle = Plateau.getInstance().getParcelle(coordonne);
                if (parcelle.getType() == couleur && parcelle.getNbBambou()== i){
                    return coordonne;
                }
            }
        }

        return super.choixDeplacementJardinier(possibilites);
    }

    /**
     * Renvoie un choix de coordonne pour la pose des parcelles parmis une liste de possibilités
     * @param possibilites
     * @param parcelle
     * @return
     */
    @Override
    public Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites,Parcelle parcelle) {
        Plateau plateau=Plateau.getInstance();

        for (Point3D coordonne : possibilites){
            if(plateau.getParcelleVoisineMemeCouleur(coordonne,parcelle).size() == 0){
                return coordonne;
            }
        }

        return super.choixCoordonnePoseParcelle(possibilites,parcelle);
    }

    /**
     * La méthode qui retourne les possibilités pour piocher une parcelle
     * @param possibilites
     * @return
     */
    @Override
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites){
        Enums.TypeParcelle couleur =  couleurParcelleDestination(1);
        for(Parcelle p : possibilites){
            if(p.getType() == couleur){
                return p;
            }
        }
        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour déplacer le panda
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        Enums.TypeParcelle couleur = couleurParcelleDestination(1);
        Plateau plateau=Plateau.getInstance();
        Joueur adversaire = plateau.getListdesjoueurs().get((plateau.getListdesjoueurs().indexOf(this)+1)%2);

        // si la couleur dispo sur le plateau on regarde s'il est accessible sinon on appelle la méthode
        Point3D point3D = ciblePanda(possibilites, couleur);
        if(point3D != null){
            return point3D;
        }

        couleur = adversaire.couleurParcelleDestination(1);
        point3D = ciblePanda(possibilites, couleur);

        for(Point3D p : possibilites){
            if(plateau.getParcelle(p).getNbBambou() > 0){
                return p;
            }
        }

        return super.choixDeplacementPanda(possibilites);
    }


    /**
     * La méthode qui renvoie le point où le panda va se déplacer
     * @param possibilites
     * @param couleur
     * @return
     */
    public Point3D ciblePanda(ArrayList<Point3D> possibilites, Enums.TypeParcelle couleur){
        for (int maxBambou = 4;maxBambou > 0; maxBambou--) {
            for (Point3D coordonne : possibilites) {
                Parcelle parcelle = Plateau.getInstance().getParcelle(coordonne);
                if (parcelle.getType() == couleur && parcelle.getNbBambou() == maxBambou) {
                    return coordonne;
                }
            }
        }
        return null;
    }

    public boolean bambouMangeableParPanda(){
        Plateau plateau = Plateau.getInstance();
        ArrayList<Point3D> destinationsPossibles = Panda.getInstance().destinationsPossibles();
        for(Point3D point3D : destinationsPossibles){
            if(plateau.getParcelle(point3D).getNbBambou() > 0){
                return true;
            }
        }
        return false;
    }

}
