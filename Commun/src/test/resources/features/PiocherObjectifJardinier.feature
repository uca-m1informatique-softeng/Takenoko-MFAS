Feature: Piocher objectif jardinier

  Scenario: Piocher un objectif jardinier
    Given Un deck de départ avec 15 objectifs jardiniers
    When Je pioche une objectif jardinier
    Then Il y a 14 objectifs jardiniers dans le deck