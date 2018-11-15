Cahiers des charges
===================

Les restrictions
----------------

-   Pas d’irrigation, de météo ou d’aménagements: Cela représente moins
    de choix pour le joueur ce qui représente une simplification du jeu

-   Pas d’objectif Parcelle: Ils sont trop compliqués à réaliser

-   Pas d’objectif multi chromatique: Il s’agit d’une facilité pour le
    code

-   Piocher un objectif réalisé: On peut le piocher même si il a été
    réaliser parce que il y a peu d’objectif différent et sans cela, les
    joueurs pourraient ne plus pouvoir piocher d’objectif

    Le jeu
    ------

-   Toutes les couleurs: Il y aura toutes les couleurs de bambous et de
    parcelle cela ne change rien à la difficultés du jeu.

-   Objectifs Jardinier et Panda: Les objectifs à réaliser seront ceux
    des Jardiniers et panda.

-   9 objectifs à réaliser: On ne change pas le nombre d’objectifs à
    réaliser car cela ne change rien au jeu.

    IA
    --

-   **IA NORMALE :**

    -   Les actions:

        -   Il pioche des objectifs jardiniers pour avoir 5 objectifs
            sur lui ou tant que la somme de ses objectifs en main et ses
            objectifs réalisés n’est pas égale au nombre d’objectifs à
            réaliser pour terminer la partie. S’il n’y a plus
            d’objectifs jardinier dans la pioche, il prend un objectif
            panda. Cette action est prioritaire et il la réalise dés que
            les conditions précédentes sont satisfaites. Il valide ses
            objectifs dès qu’ils sont réalisés.

        -   Ensuite, pioche des parcelles. Son but est d’avoir 2
            parcelles de chaque couleur cote à cote, cela permet de ne
            pas se faire contrer par le panda et avoir du bambou qu’il
            peut manger pour réaliser son objectif panda en parallèle de
            ses objectifs jardinier. S’il n’a pas pioché d’objectif à ce
            tour, et qu’il n’y a pas les 3 couleurs de parcelles sur le
            terrain, il pioche une parcelle et déplacera le jardinier
            ensuite. Sinon, il vérifie qu’il y a deux parcelles de la
            couleur de ses objectifs jardiniers les plus présents en
            main. S’il y en a deux, il déplace le jardinier, sinon il
            pioche pour en avoir 2. Au niveau des choix de couleurs: Il
            choisit la couleur proposée par le jeu qui est la plus
            présente dans sa main d’objectifs jardiniers à réaliser
            Sinon, il privilégie les roses car moins nombreuses dans la
            pioche, puis les jaunes et enfin les vertes. Si les
            parcelles sont posées comme il le souhaite, il ne piochera
            plus de parcelles

        -   Pour le déplacement du jardinier, une fois les parcelles
            disposées comme il le souhaite, il va déplacer le jardinier
            afin d’avoir 4 bambous sur chaque couleur. Il va le déplacer
            vers la couleur la plus représentée dans ses objectifs à
            réaliser, s’il ne peut pas, il essaie de compléter les
            autres couleurs et s’il ne peut toujours pas, il va déplacer
            le panda en fonction de son objectif panda (s’il peut
            atteindre la parcelle voulue en un seul coup) sinon il
            déplacera le jardinier pour atteindre la parcelle nécessaire
            à la réalisation de l’objectif en deux tours. S’il n’a pas
            de parcelle où il a besoin de faire pousser du bambou, il
            déplacera le panda.

        -   Pour le déplacement du panda, il essaie de valider son
            objectif panda de départ (s’il y a du bambou de sa couleur
            atteignable par le panda). S’il ne peut pas atteindre le
            bambou voulu, il revient sur le déplacement du jardinier
            pour atteindre la parcelle où il souhaite faire pousser du
            bambou en deux coups.

-   **IA AVANCE** Au 1er tour: S’il commence, il pioche et pose une
    parcelle, et déplace le panda sur celle ci. Sinon il pioche un
    objectif jardinier et déplace le panda s’il peut manger un bambou,
    sinon il pose une parcelle

    -   Les actions:

        -   Il pioche des objectifs jardiniers pour avoir 5 objectifs
            sur lui ou tant que la somme de ses objectifs jardinier en
            main et réalisés n’est pas égale au nombre d’objectifs à
            réaliser pour terminer la partie moins 1. (Par exemple, s’il
            y a 9 objectifs à réaliser pour terminer la partie, il va
            vouloir réaliser 8 objectifs jardinier). S’il n’y a plus
            d’objectif jardinier dans la pioche, il prend un objectif
            panda. Une fois qu’il a réalisé ses 8 objectifs (en restant
            dans l’exemple), il pioche des objectifs panda jusqu’à en
            avoir 5 en main. Il valide ses objectifs jardinier
            automatiquement s’ils sont réalisés. S’il a déjà réalisé 8
            objectifs: Si l’adversaire a réalisé ses 9 objectifs, il
            valide les siens Sinon, il attend d’avoir 5 objectifs qu’il
            peut valider en main. L’action de piocher un objectif est
            prioritaire et il la réalise dès que les conditions
            précédentes sont satisfaites.

        -   Pour le déplacement du panda, il calcule combien de bambou
            au maximum il aurait besoin en fin de partie pour réaliser 5
            objectifs panda d’un coup, il va donc essayer d’accumuler un
            maximum de bambou. Il déplace le panda de manière à essayer
            de prendre un bambou et le placer sur une parcelle où il n’y
            a plus de bambou atteignable (afin de contrer l’adversaire).
            S’il ne peut pas atteindre de bambou: S’il n’a pas pris
            d’objectif à ce tour, il déplace le jardinier pour faire
            pousser un bambou qu’il pourra manger ensuite. S’il a déjà
            pris un objectif, il posera une parcelle .

        -   Pour le déplacement du jardinier, s’il n’y a pas de bambou
            en jeu il va jouer le jardinier afin de faire pousser des
            bambous et ensuite pouvoir joué le panda dans les tours
            suivants afin de réaliser ses objectifs panda. S’il réalise
            tous les objectifs panda il va se concentrer sur les
            objectifs jardinier et déplacer le jardinier sur le plateau
            afin d’avoir 4 bambous sur chaque couleur.

        -   Pour poser une parcelle, il posera une parcelle seulement
            s’il n’y a pas la couleur dont il a besoin pour réaliser ses
            objectifs.


