package takenoko.client;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * C'est la classe du Client
 */
@RestController
public class Client {

    private int ID;

    private int CLIENT;

    private RestTemplate server;

    private String HTTP_SERVER;

    private String HTTP_CLIENT;

    private int portClient;
    private String hostClient;

    private int portServeur;
    private String hostServeur;

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getID(){
        return this.ID;
    }

    public int getPort(){
        return portClient;
    }

    public int getClient(){
        return CLIENT;
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Le constructeur
     * @param SERVER_PORT
     * @param SERVER_HOST
     * @param CLIENT_PORT
     * @param CLIENT_HOST
     */
    public Client(@Qualifier("SERVER_PORT") int SERVER_PORT, @Qualifier("SERVER_HOST") String SERVER_HOST , @Qualifier("CLIENT_PORT") int CLIENT_PORT, @Qualifier("CLIENT_HOST") String CLIENT_HOST){
        this.server = new RestTemplate();


        this.hostServeur = SERVER_HOST;
        this.portServeur = SERVER_PORT;

        this.hostClient = CLIENT_HOST;
        this.portClient = CLIENT_PORT;

        this.HTTP_SERVER = "http://" + SERVER_HOST + ":" + SERVER_PORT;
        this.HTTP_CLIENT = "http://" + CLIENT_HOST + ":" + CLIENT_PORT;
    }





}
