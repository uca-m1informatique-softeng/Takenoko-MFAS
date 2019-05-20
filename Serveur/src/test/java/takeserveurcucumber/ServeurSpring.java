package takeserveurcucumber;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import takeserveur.MainServeur;
import takeserveur.Serveur;

@SpringBootTest(classes = MainServeur.class)
@ContextConfiguration()
public class ServeurSpring {
    Serveur serveur = new Serveur(8080,"localhost",1);
}
