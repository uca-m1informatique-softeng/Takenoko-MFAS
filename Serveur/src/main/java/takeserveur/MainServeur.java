package takeserveur;

import javafx.geometry.Point3D;
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
            System.out.println("Démarrage du serveur sur le port "+ serveur.getPort());
            serveur.setDeck(jeuServeur.getDeck().versJson());
            //serveur.setPlateau(jeuServeur.getPlateau().versJsonP());
            //System.out.println("nbc ="+serveur.getNbClient()+" nbr = "+serveur.getNbClientReady());
            //System.out.println("longuur du plateau "+jeuServeur.getPlateau().getKeylist().size());
            //System.out.println("couleur derniere parcelle "+jeuServeur.getPlateau().getParcelle(new Point3D(0,1, -1)));
            while(!serveur.isPartiePrete()){
            }
            //serveur.piocher();
            /*jeuServeur.piocher_poser();
            jeuServeur.piocher_poser();
            jeuServeur.piocher_poser();

            serveur.setDeck(jeuServeur.getDeck().versJson());
            serveur.setPlateau(jeuServeur.getPlateau().versJsonP());

            System.out.println("APRES longuur du plateau SERVEUR "+jeuServeur.getPlateau().getKeylist().size());
            System.out.println("APRES couleur derniere parcelle SERVEUR "+jeuServeur.getPlateau().getParcelle(new Point3D(0,1, -1)));*/
        };
    }
}
