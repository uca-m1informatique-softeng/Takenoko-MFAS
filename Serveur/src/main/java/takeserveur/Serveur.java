package takeserveur;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * C'est la classe du serveur
 */
@RestController
public class Serveur {



    private int nbClient;

    private int nbClientReady;

    private int debut;

    private int portServeur;
    private String hostServeur;

    private RestTemplate client;

    private JSONObject deck;

    @Autowired
    private JeuServeur jeuServeur;

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getNbClient() {
        return nbClient;
    }

    public void setNbClient(int nbClient) {
        this.nbClient = nbClient;
    }

    public int getNbClientReady() {
        return nbClientReady;
    }

    public void setNbClientReady(int nbClientReady) {
        this.nbClientReady = nbClientReady;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getPortServeur() {
        return portServeur;
    }

    public void setPortServeur(int portServeur) {
        this.portServeur = portServeur;
    }

    public String getHostServeur() {
        return hostServeur;
    }

    public void setHostServeur(String hostServeur) {
        this.hostServeur = hostServeur;
    }

    public RestTemplate getClient() {
        return client;
    }

    public void setClient(RestTemplate client) {
        this.client = client;
    }

    public JSONObject getDeck() {
        return deck;
    }

    public void setDeck(JSONObject deck) {
        this.deck = deck;
    }
    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Le constructeur
     * @param portServeur
     * @param hostServeur
     * @param nbClient
     */
    public Serveur(@Qualifier("portServeur") int portServeur, @Qualifier("hostServeur") String hostServeur, @Qualifier("client") int nbClient ){
        this.client = new RestTemplate();
        this.portServeur = portServeur;

        this.hostServeur = hostServeur;
        this.nbClient = nbClient;
        this.nbClientReady = 0;
        this.debut = 0;
        this.deck = new JSONObject();
    }


    /**
     * @return
     */
    public boolean isPartiePrete(){
        if(nbClientReady >= nbClient && debut>0) {
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    @RequestMapping("/nouvelle-connexion")
    public Integer acceptConnection(){
        System.out.println("connexion...");
        this.nbClientReady = 1;
        return 1;
    }

    /**
     * @return
     */
    @RequestMapping("/type_bot")
    public Integer type_bot(){
        System.out.println("envoie du type");
        this.debut = 1;
        return 1;
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping("/partie")
    public String partie() throws Exception{

        return jeuServeur.game(type().intValue());
    }

    /**
     * @return
     */
    public Integer type(){
        return Integer.parseInt(client.getForObject("http://localhost:8088/type",String.class));
    }

    /**
     * @return
     */
    @RequestMapping("/deck")
    public String sendDeck(){
        return deck.toString();
    }



}
