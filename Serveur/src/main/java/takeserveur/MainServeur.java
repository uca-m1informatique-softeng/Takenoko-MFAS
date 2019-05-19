package takeserveur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * C'est la classe principal du serveur
 */
@SpringBootApplication
public class MainServeur {


    @Qualifier("serveur")
    @Autowired
    Serveur serveur;

    @Autowired
    JeuServeur jeuServeur;

    public static void main (String[] args){
        SpringApplication.run(MainServeur.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Démarrage du serveur sur le port "+ serveur.getPortServeur());
            serveur.setDeck(jeuServeur.getDeck().versJson());
            while(!serveur.isPartiePrete()){
            }
            serveur.partie();
        };
    }
}
