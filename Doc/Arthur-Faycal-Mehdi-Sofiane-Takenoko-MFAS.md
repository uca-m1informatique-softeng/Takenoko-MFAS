# Feedback de la première livraison

Groupe https://github.com/orgs/uca-m1informatique-softeng/teams/bonjour

## Intégration continue ##
C’est bien opérationnel : https://travis-ci.com/uca-m1informatique-softeng/Takenoko-MFAS/builds/

## Gestion de projet ##
vous avez utilisé des milestones, des issues et un kanban : c’est bien.

Une remaque pour toujours faire mieux : dans les descriptions des tâches, etc., mettez le numéro d'issue en référence (ex: pas "tâche du slice 1", mais "tâche du slice 1, #1"). Il faudra travailler le nom des slices (ex : "Slice 1 : Introduction des composants" est en fait plus que cela car il y a travis-ci et cucumber avec ; le slice 4 à venir a un nom trop générique et surfait, qui ne signie donc rien )

## TDD appliqué à Takenoko ##
1 scénario de fait (un plateau avec une parcelle + une autre, cela fait 2 parcelles).

## Etude d’architecture sur le découpage en services REST ##
Pour l'application des webservices, vous n'avez pas utilisé spring mais un autre framework de java (javax.ws). D'ailleurs, il ne s'agit pas de faire des jsp (page web java), mais de faire échanger un client et un serveur (appel d'un service, d'api). Par ailleurs, ce que vous exposez dans "Serveur" n'est pas appelé.

Vous avez fait (bien) évoluer les aspects composants.

L'étude proposée est trop partielle. Elle décrit une première approche. Il vous était demandé une vue d'ensemble. Par ailleurs, vous semblez être sur une fausse route technologique. Il s'agit de faire en sorte que vos bots deviennent des programmes indépendant de Partie, et que Partie et les bots échangent via des web services.

Il est conseillé d'expliciter l'ensemble des routes/échanges dans le scénario.
