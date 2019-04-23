package takenoko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import takenoko.serveur.Jeu;
import takenoko.serveur.Serveur;



@SpringBootApplication
public class MainServeur {


    @Qualifier("serveur")
    @Autowired
    Serveur serveur;

    @Autowired
    Jeu jeu;

    public static void main (String[] args){
        SpringApplication.run(MainServeur.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Démarrage du serveur sur le port " + serveur.getPort());
            while(!serveur.isPartiePrete()){}
            jeu.jouer();
        };
    }
}
