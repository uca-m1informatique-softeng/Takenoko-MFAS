package takeclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * La classe principal du client
 */
@SpringBootApplication
public class MainClient {

    @Qualifier("client")
    @Autowired
    Client client;

    public static void main (String[] args){
        SpringApplication.run(MainClient.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Démarrage du client sur le port " + client.getPortClient());
            boolean connected = client.connect();
            if(connected){
                while(!client.getType()){

                }
                client.envoie();
            }
            else {
                System.out.println("Connexion refusée");
            }

        };
    }
}
