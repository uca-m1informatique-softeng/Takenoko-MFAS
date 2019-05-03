Feature: Piocher objectif panda

  Scenario: Piocher un objectif panda
    Given Un deck de départ avec 15 objectifs pandas
    When Je pioche une objectif panda
    Then Il y a 14 objectifs pandas dans le deck