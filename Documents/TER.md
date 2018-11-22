Cahiers des charges
===================

Les restrictions
----------------

-   Pas d’irrigation, de météo ou d’aménagements: Cela représente moins
    de choix pour le joueur ce qui représente une simplification du jeu

-   Pas d’objectif Parcelle: Ils sont trop compliqués à réaliser

-   Pas d’objectif multi chromatique et d’objectif jardinier où il faut
    faire pousser du bambou sur plusieurs parcelles: Il s’agit d’une
    facilité pour le code

-   Le jeu est visible: Cela permet de prévoir plus facilement le jeu de
    l’adversaire

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

    **Les actions:**

    1.  <span>La pioche d’objectifs</span> Il pioche des objectifs
        jardiniers pour avoir 5 objectifs sur lui ou tant que la somme
        de ses objectifs en main et ses objectifs réalisés n’est pas
        égale au nombre d’objectifs à réaliser pour terminer la partie.
        S’il n’y a plus d’objectifs jardinier dans la pioche, il prend
        un objectif panda. Cette action est prioritaire et il la réalise
        dés que les conditions précédentes sont satisfaites. Il valide
        ses objectifs dès qu’ils sont réalisés.

    2.  <span>Poser une parcelle </span> Son but est d’avoir 2 parcelles
        côte à côte de la couleur la plus représentée parmi ses
        objectifs. S’il a le même nombre d’objectif de différente
        couleur, il choisit la couleur de l’objectif qui rapporte le
        plus du point. Avoir deux parcelles côte à côte permet de ne pas
        se faire contrer par le panda et avoir du bambou qu’il peut
        manger pour réaliser son objectif panda en parallèle de ses
        objectifs jardinier. S’il y a les deux parcelles sur le plateau,
        il déplace alors le jardinier.

        Au niveau des choix de couleurs: Il choisit la couleur proposée
        par le jeu qui est la plus présente dans sa main d’objectifs
        jardiniers à réaliser. Sinon, il privilégie les roses car moins
        nombreuses dans la pioche, puis les jaunes et enfin les vertes.
        Si les parcelles sont posées comme il le souhaite, il ne
        piochera plus de parcelles

    3.  <span>Le déplacement du jardinier</span> une fois les parcelles
        disposées comme il le souhaite, il va déplacer le jardinier. Il
        va le déplacer vers la couleur la plus représentée dans ses
        objectifs à réaliser, s’il ne peut pas, il essaie de compléter
        les autres couleurs et s’il ne peut toujours pas, il va déplacer
        le panda. S’il n’a pas besoin de faire pousser du bambou, il
        déplacera le panda.

    4.  <span>Le déplacement du panda</span> Il essaie de valider son
        objectif panda en allant sur la parcelle de la couleur
        nécessaire ou en s’en rapprochant.

-   **IA AVANCE** Au 1er tour: S’il commence, il pioche et pose une
    parcelle, et déplace le panda sur celle ci. Sinon il pioche un
    objectif et déplace le panda s’il peut manger un bambou, sinon il
    pose une parcelle

    Le joueur a deux stratégies selon les objectifs que vise son
    adversaire. Une stratégie panda si l’adversaire a plus d’objectif
    panda que d’objectif jardinier et une stratégie jardinier sinon.

    **Les actions:**

    1.  <span>La pioche d’objectifs</span>

        1.  **Pour la stratégie jardinier**: Il pioche des objectifs
            jardiniers pour avoir 5 objectifs sur lui ou tant que la
            somme de ses objectifs jardinier en main et réalisés n’est
            pas égale au nombre d’objectifs à réaliser pour terminer la
            partie moins 1. (Par exemple, s’il y a 9 objectifs à
            réaliser pour terminer la partie, il va vouloir réaliser 8
            objectifs jardinier). S’il n’y a plus d’objectif jardinier
            dans la pioche, il prend un objectif panda. Une fois qu’il a
            réalisé ses 8 objectifs (en restant dans l’exemple),s’il est
            sûr de battre son adversaire en validant un dernier
            objectif, il le fait sinon il pioche des objectifs panda
            jusqu’à en avoir 5 en main. Il valide ses objectifs
            jardinier automatiquement s’ils sont réalisés. S’il a déjà
            réalisé 8 objectifs: Si l’adversaire a réalisé ses 9
            objectifs, il valide les siens Sinon, il attend d’avoir 5
            objectifs qu’il peut valider en main. L’action de piocher un
            objectif est prioritaire et il la réalise dès que les
            conditions précédentes sont satisfaites.

        2.  **Pour la stratégie panda**: Il agit comme la stratégie
            jardinier en piochant des objectifs panda à la place des
            objectifs jardinier et des objectifs jardinier à la place
            des objectifs panda.

    2.  <span>Le déplacement du panda</span>

        1.  **Pour la stratégie jardinier**: Il calcule (en fonction des
            objectifs de l’adversaire et des objectifs restant) combien
            de bambou au maximum il aurait besoin en fin de partie pour
            réaliser 5 objectifs panda d’un coup, il va donc essayer
            d’accumuler un maximum de bambou. S’il a des objectifs
            jardinier identiques à celui de son adversaire, il déplace
            le panda de manière à essayer de prendre un bambou et le
            placer sur une parcelle où il n’y a plus de bambou
            atteignable (afin de contrer l’adversaire) S’il ne peut pas
            atteindre de bambou, il pose une parcelle. S’il n’a pas
            d’objectifs jardinier identiques à celui de son
            adversaire,il posera une parcelle seulement s’il n’y a pas
            la couleur dont il a besoin pour réaliser ses objectifs,
            sinon il déplace le jardinier.

        2.  **Pour la stratégie panda**: Si on a pioché un objectif, on
            déplace le panda si on peut manger du bambou sinon on pose
            une parcelle qui ne sera pas atteignable par le panda.
            Sinon, si un bambou nécessaire à la réalisation de nos
            objectifs est accessible, on le mange. Sinon on joue le
            jardinier ou une parcelle pour créer un bambou accessible.

    3.  <span>Poser une parcelle</span> Il posera une parcelle seulement
        s’il n’y a pas la couleur dont il a besoin pour réaliser ses
        objectifs ou s’il a besoin d’une parcelle dans les cas citez
        précédemment. Il pose les parcelles de façon à ce qu’il n’y ait
        pas des parcelles de même couleur adjacentes.

    4.  <span>Le déplacement du jardinier</span>

        1.  **Pour la stratégie jardinier**: S’il n’y a pas de bambou en
            jeu il va jouer le jardinier afin de faire pousser des
            bambous et ensuite pouvoir jouer le panda dans les tours
            suivants afin d’accumuler du bambou. S’il a suffisamment de
            bambou, il va se concentrer sur les objectifs jardinier et
            déplacer le jardinier afin de les réaliser. De plus, comme
            dit précédemment, si ses objectifs jardiner et ceux de sont
            adversaire divergent, il joue le jardinier afin de réaliser
            ses objectifs.

        2.  **Pour la stratégie panda**: Il joue le jardinier seulement
            s’il doit réaliser des objectifs panda et qu’il n’y a pas de
            bambou a manger ou s’il ne reste plus que des objectifs
            jardinier.


