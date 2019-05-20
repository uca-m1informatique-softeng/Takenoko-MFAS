# Architecture REST + Spring

## Berriri Mehdi, Bounab Fayçal, Djema Sofiane

### 1. Découpage Rest

Notre projet et découper en 3 partie : 
Serveur,Client et Commun .
La partie commune contient notre moteur du Takenoko .
La partie serveur et client contiennent respectivement 4 et 3 classes : 
* JeuServeur : classe où il y a la boucle principale du jeu (n'existe pas dans la partie Client)
* Serveur/Client : contiennent les différents chemins 
* ConfigurationServeur/ConfigurationClient : configuration des différents ports
* MainServeur/MainClient : classe qui contient le Main du serveur et client

### 2. Documentation des routes

#### Coté client :

* "/panda" : donne un strategie panda au client 
* "/jardinier" : donne un strategie jardinier au client 
* "/parcelle" : donne un strategie parcelle au client 
* "/random" : donne un strategie random au client 
* "/type" : donne la strategie du client au serveur
* "/mon_score" : donne le score final de la partie


#### Coté serveur :

* "/type_bot" : incrémente le nombre de clients ayant choisi leur stratégie serveur 
* "/nouvelle-connexion" : incrémente le nombre de clients connectés dans serveur 
* "/resultat" : le client recupere le score final à partie de ce lien

### 3. Déroulement de la partie

Pour démarrer la partie nous lançons d'abord le serveur qui attendra un certain nombre de clients pour lancer la partie ; ces clients devront d'abord choisir la stratégie qu'ils veulent adopter, si aucune stratégie n'est choisie le client adoptera la stratégie du bot random . 
Les clients se connectent au serveur, après ça il faudra ouvrir un navigateur web et choisir le type de stratégie et confirmer, une fois cette étape finie le serveur lance la partie et affiche le score et la couleur du client qui a gagné.

### 4. Organisation des composants Spring

Le Main du client et du serveur sont des @SpringBootApplication ,
 il y a aussi des @Bean pour les ports et les hosts du client 
 et serveur dans les différentes configurations , 
 les classes "Client" et "Serveur" sont des @RestController.

