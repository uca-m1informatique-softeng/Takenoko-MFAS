package takenoko.moteur;


import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import takenoko.moteur.objectifs.Objectif;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.Enums.TypeParcelle;
import takenoko.moteur.objectifs.ObjectifParcelle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe des decks
 */
@Component
@Scope("singleton")
public class Deck {

    private static Deck instance=null;
    private ArrayList<ObjectifJardinier> deckObjectifsJardinier = new ArrayList<>();
    private ArrayList<ObjectifPanda> deckObjectifsPanda = new ArrayList<>();
    private ArrayList<ObjectifParcelle> deckObjectifsParcelle = new ArrayList<>();
    private ArrayList<Parcelle> deckParcelles = new ArrayList<>();

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public ArrayList<ObjectifJardinier> getDeckObjectifsJardinier() {
        return deckObjectifsJardinier;
    }

    public void setDeckObjectifsJardinier(ArrayList<ObjectifJardinier> deckObjectifsJardinier) {
        this.deckObjectifsJardinier = deckObjectifsJardinier;
    }

    public ArrayList<ObjectifPanda> getDeckObjectifsPanda() {
        return deckObjectifsPanda;
    }

    public void setDeckObjectifsPanda(ArrayList<ObjectifPanda> deckObjectifsPanda) {
        this.deckObjectifsPanda = deckObjectifsPanda;
    }

    public ArrayList<ObjectifParcelle> getDeckObjectifsParcelle() {
        return deckObjectifsParcelle;
    }

    public void setDeckObjectifsParcelle(ArrayList<ObjectifParcelle> deckObjectifsParcelle) {
        this.deckObjectifsParcelle = deckObjectifsParcelle;
    }

    public ArrayList<Parcelle> getDeckParcelles() {
        return deckParcelles;
    }

    public void setDeckParcelles(ArrayList<Parcelle> deckParcelles) {
        this.deckParcelles = deckParcelles;
    }



    //////////////////////////////Méthodes//////////////////////////////

    public static final Deck getInstance() {
        if (Deck.instance == null) {
            Deck.instance = new Deck();
            Deck.instance.resetDeck();
        }

        return Deck.instance;
    }

    /**
     * Réinitialise le deck
     */
    public void resetDeck(){
        initialiserObjectifsJardinier();
        initialiserObjectifsPanda();
        initialiserDeckParcelle();
        initialiserObjectifParcelle();
    }

    /**
     * Initialise le deck des objectifs parcelles
     */

    public void initialiserObjectifParcelle() {

        deckObjectifsParcelle.clear();
        for (int i = 0; i < 3; i++) {
            ObjectifParcelle objectifParcelle = new ObjectifParcelle();
            objectifParcelle.setValeur(3);
            objectifParcelle.setCouleur(TypeParcelle.JAUNE);
            objectifParcelle.setType(i);
            deckObjectifsParcelle.add(objectifParcelle);
        }

        for (int i = 0; i < 2; i++) {
            ObjectifParcelle objectifParcelle4Jaune = new ObjectifParcelle();
            objectifParcelle4Jaune.setValeur(4);
            objectifParcelle4Jaune.setCouleur(TypeParcelle.JAUNE);
            objectifParcelle4Jaune.setType(3);
            deckObjectifsParcelle.add(objectifParcelle4Jaune);
        }


        for (int i = 0; i < 3; i++) {
            ObjectifParcelle objectifParcelle = new ObjectifParcelle();
            objectifParcelle.setValeur(2);
            objectifParcelle.setCouleur(TypeParcelle.VERTE);
            objectifParcelle.setType(i);
            deckObjectifsParcelle.add(objectifParcelle);
        }

        for (int i = 0; i < 2; i++) {
            ObjectifParcelle objectifParcelle4Vert = new ObjectifParcelle();
            objectifParcelle4Vert.setValeur(3);
            objectifParcelle4Vert.setCouleur(TypeParcelle.VERTE);
            objectifParcelle4Vert.setType(3);
            deckObjectifsParcelle.add(objectifParcelle4Vert);
        }



        for (int i = 0; i < 3; i++) {
            ObjectifParcelle objectifParcelle = new ObjectifParcelle();
            objectifParcelle.setValeur(4);
            objectifParcelle.setCouleur(TypeParcelle.ROSE);
            objectifParcelle.setType(i);
            deckObjectifsParcelle.add(objectifParcelle);
        }
        for (int i = 0; i < 2; i++) {
            ObjectifParcelle objectifParcelle4Rose = new ObjectifParcelle();
            objectifParcelle4Rose.setValeur(5);
            objectifParcelle4Rose.setCouleur(TypeParcelle.ROSE);
            objectifParcelle4Rose.setType(3);
            deckObjectifsParcelle.add(objectifParcelle4Rose);
        }

    }

    /**
     * Initialise le deck des parcelles
     */
    public void initialiserDeckParcelle() {
        deckParcelles.clear();
        for (int i = 0;i < 11;i++){
            Parcelle parcelle = new Parcelle();
            parcelle.setType(TypeParcelle.VERTE);
            parcelle.setIrriguee(false);
            deckParcelles.add(parcelle);
        }
        for (int i = 11;i < 18;i++){
            Parcelle parcelle = new Parcelle();
            parcelle.setType(TypeParcelle.ROSE);
            parcelle.setIrriguee(false);
            deckParcelles.add(parcelle);
        }
        for (int i = 18;i < 27;i++){
            Parcelle parcelle = new Parcelle();
            parcelle.setType(TypeParcelle.JAUNE);
            parcelle.setIrriguee(false);
            deckParcelles.add(parcelle);
        }

    }

    /**
     * Initialise le deck des objectifs jardinier
     */
    public void initialiserObjectifsJardinier(){
        deckObjectifsJardinier.clear();
        for (int i = 0;i<5;i++){
            ObjectifJardinier objectifJardinier = new ObjectifJardinier();
            objectifJardinier.setValeur(6);
            objectifJardinier.setCouleur(TypeParcelle.JAUNE);
            objectifJardinier.setTailleBambou(4);
            deckObjectifsJardinier.add(objectifJardinier);

        }

        for (int i = 5;i<10;i++){
            ObjectifJardinier objectifJardinier = new ObjectifJardinier();
            objectifJardinier.setValeur(7);
            objectifJardinier.setCouleur(TypeParcelle.ROSE);
            objectifJardinier.setTailleBambou(4);
            deckObjectifsJardinier.add(objectifJardinier);
        }
        for (int i = 10;i<15;i++){

            ObjectifJardinier objectifJardinier = new ObjectifJardinier();
            objectifJardinier.setValeur(5);
            objectifJardinier.setCouleur(TypeParcelle.VERTE);
            objectifJardinier.setTailleBambou(4);
            deckObjectifsJardinier.add(objectifJardinier);

        }



    }

    /**
     * Initialise le deck des objectifs panda
     */
    public void initialiserObjectifsPanda(){

        deckObjectifsPanda.clear();
        for (int i = 0;i<4;i++){
            ObjectifPanda objectifPanda = new ObjectifPanda();
            objectifPanda.setValeur(4);
            objectifPanda.setCouleur(TypeParcelle.JAUNE);
            objectifPanda.setNombreBambou(2);

            deckObjectifsPanda.add(objectifPanda);
        }

        for (int i = 4;i<7;i++){
            ObjectifPanda objectifPanda = new ObjectifPanda();
            objectifPanda.setValeur(5);
            objectifPanda.setCouleur(TypeParcelle.ROSE);
            objectifPanda.setNombreBambou(2);

            deckObjectifsPanda.add(objectifPanda);
        }
        for (int i = 7;i<12;i++){
            ObjectifPanda objectifPanda = new ObjectifPanda();
            objectifPanda.setValeur(3);
            objectifPanda.setCouleur(TypeParcelle.VERTE);
            objectifPanda.setNombreBambou(2);

            deckObjectifsPanda.add(objectifPanda);
        }

        ObjectifPanda objectifPandaJaune = new ObjectifPanda();
        objectifPandaJaune.setValeur(6);
        objectifPandaJaune.setCouleur(TypeParcelle.JAUNE);
        objectifPandaJaune.setNombreBambou(3);
        deckObjectifsPanda.add(objectifPandaJaune);

        ObjectifPanda objectifPandaVert = new ObjectifPanda();
        objectifPandaVert.setValeur(6);
        objectifPandaVert.setCouleur(TypeParcelle.VERTE);
        objectifPandaVert.setNombreBambou(3);
        deckObjectifsPanda.add(objectifPandaVert);


        ObjectifPanda objectifPandaRose = new ObjectifPanda();
        objectifPandaRose.setValeur(6);
        objectifPandaRose.setCouleur(TypeParcelle.ROSE);
        objectifPandaRose.setNombreBambou(3);
        deckObjectifsPanda.add(objectifPandaRose);


    }


    /**
     * La pioche des parcelles
     * @return
     */
    public ArrayList<Parcelle> piocherParcelle(){
        ArrayList<Parcelle> listeParcelleTemporaire = new ArrayList<>();

        int nombreparcelle=3;
        if(deckParcelles.size() < 3){

            nombreparcelle=deckParcelles.size();
        }

        for (int i = 0; i < nombreparcelle; i++) {
            int random = new Random().nextInt(deckParcelles.size());
            listeParcelleTemporaire.add(deckParcelles.get(random));
            deckParcelles.remove(random);
        }

        return listeParcelleTemporaire;
    }
    /**
     * La méthode qui remet les deux parcelles dans le deck
     * @param listParcelle
     */
    public void remettreParcellesDansDeck(ArrayList<Parcelle> listParcelle){
        deckParcelles.addAll(listParcelle);
    }

    /**
     * La pioche des objectifs parcelles
     */
    public ObjectifParcelle piocheObjectifParcelle(){
        return (ObjectifParcelle)piocheobjectif(deckObjectifsParcelle);
    }


    /**
     * La pioche des objectifs jardinier
     */
    public ObjectifJardinier piocheObjectifJardinier() {
        return (ObjectifJardinier)piocheobjectif(deckObjectifsJardinier);
    }

    /**
     * La pioche des objectifs panda
     */
    public ObjectifPanda piocheObjectifPanda() {
        return (ObjectifPanda)piocheobjectif(deckObjectifsPanda);
    }

    /**
     * La pioche des objectifs
     * @param list
     * @return
     */
    private Objectif piocheobjectif(ArrayList<?> list){
        if (!list.isEmpty()) {
            int random = new Random().nextInt(list.size());
            Objectif objtemp =(Objectif) list.get(random);
            list.remove(random);
            Affichage.affichagePiocheObjectif(objtemp);
            return objtemp;

        }
        return null;
    }

    ///////////// JSON //////////////

    public JSONObject versJson() throws Exception{
        JSONObject deckJ = new JSONObject(); // deck qui contient cartes objectifs + tuiles

        ArrayList<Parcelle> ParcelleNew = new ArrayList<>();
        ParcelleNew.addAll(deckParcelles);
        JSONObject JsParcelle = new JSONObject();
        for (int i = 0; i < ParcelleNew.size(); i++) {
            JsParcelle.put(i+"",ParcelleNew.get(i).toJson());
        }

        ArrayList<ObjectifJardinier> ParcelleNew1 = new ArrayList<>();
        ParcelleNew1.addAll(deckObjectifsJardinier);
        JSONObject JsObJar = new JSONObject();
        for (int i = 0; i < ParcelleNew1.size(); i++) {
            JsObJar.put(i+"",ParcelleNew1.get(i).toJson());
        }

        ArrayList<ObjectifPanda> ParcelleNew2 = new ArrayList<>();
        ParcelleNew2.addAll(deckObjectifsPanda);
        JSONObject JsObPan = new JSONObject();
        for (int i = 0; i < ParcelleNew2.size(); i++) {
            JsObPan.put(i+"",ParcelleNew2.get(i).toJson());
        }

        ArrayList<ObjectifParcelle> ParcelleNew3 = new ArrayList<>();
        ParcelleNew3.addAll(deckObjectifsParcelle);
        JSONObject JsObPar = new JSONObject();
        for (int i = 0; i < ParcelleNew3.size(); i++) {
            JsObPar.put(i+"",ParcelleNew3.get(i).toJson());
        }


        deckJ.put("Parcelle",JsParcelle);
        deckJ.put("ObjJar",JsObJar);
        deckJ.put("ObjPan",JsObPan);
        deckJ.put("ObjPar",JsObPar);

        return deckJ;


    }

    public void deJson(JSONObject deck){
        JSONObject JsParcelle = deck.getJSONObject("Parcelle");
        JSONObject JsObJar = deck.getJSONObject("ObjJar");
        JSONObject JsObPan = deck.getJSONObject("ObjPan");
        JSONObject JsObPar = deck.getJSONObject("ObjPar");


        // Parcelles
        ArrayList<Parcelle> Par= new ArrayList<>();
        for (int i = 0; i < JsParcelle.length(); i++) {
            JSONObject obj = JsParcelle.getJSONObject(i+"");
            Parcelle New = new Parcelle();
            New.setType(New.FromJson(obj));
            Par.add(New);
        }

        // Objectifs Jardiniers
        ArrayList<ObjectifJardinier> Jar = new ArrayList<>();
        for (int i = 0; i < JsParcelle.length(); i++) {
            JSONObject obj = JsObJar.getJSONObject(i+"");
            ObjectifJardinier New = new ObjectifJardinier();
            New.setValeur(obj.getInt("valeur"));
            New.setCouleur(New.FromJson(obj));
            New.setTailleBambou(obj.getInt("tailleBambou"));
            Jar.add(New);
        }

        // Objectifs Pandas
        ArrayList<ObjectifPanda> Pan = new ArrayList<>();
        for (int i = 0; i < JsParcelle.length(); i++) {
            JSONObject obj = JsObPan.getJSONObject(i+"");
            ObjectifPanda New = new ObjectifPanda();
            New.setValeur(obj.getInt("valeur"));
            New.setCouleur(New.FromJson(obj));
            New.setNombreBambou(obj.getInt("nombreBambou"));
            Pan.add(New);

        }

        // Objectifs Jardiniers
        ArrayList<ObjectifParcelle> Mot = new ArrayList<>();
        for (int i = 0; i < JsParcelle.length(); i++) {
            JSONObject obj = JsObPar.getJSONObject(i+"");
            ObjectifParcelle New = new ObjectifParcelle();
            New.setValeur(obj.getInt("valeur"));
            New.setCouleur(New.FromJson(obj));
            Mot.add(New);
        }


        this.deckParcelles = Par;
        this.deckObjectifsJardinier = Jar;
        this.deckObjectifsPanda = Pan;
        this.deckObjectifsParcelle = Mot;

    }

    public String toString(){
        String string = "";
        for (Parcelle par: deckParcelles) {
            string += "parcelle de coulur "+par.getType().toString()+"\n";

        }
        return string;
    }


    /**
     * La pioche de l'irrigation
     * @return
     */
    public Irrigation piocheIrrigation(){
        return new Irrigation();
    }

    /**
     * Renvoie si le deck des parcelles est vide
     * @return
     */
    public boolean isDeckParcelleVide(){
        return deckParcelles.isEmpty();
    }

    /**
     * Envoie si le deck des objectifs panda est vide
     * @return
     */
    public boolean isDeckObjectifPandaVide(){
        return deckObjectifsPanda.isEmpty();
    }

    /**
     * Envoie si le deck des objectifs jardinier est vide
     * @return
     */
    public boolean isDeckObjectifJardinierVide(){
        return deckObjectifsJardinier.isEmpty();
    }

    /**
     * Envoie si le deck des objectifs parcelle est vide
     * @return
     */
    public boolean isDeckObjectifParcelleVide(){
        return deckObjectifsParcelle.isEmpty();
    }

}
