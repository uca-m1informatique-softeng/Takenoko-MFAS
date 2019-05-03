Feature: Poser Parcelle

  Scenario: Poser une première parcelle
    Given Un Plateau de départ avec 1 Parcelle
    When Je pose une Parcelle sur le Plateau
    Then Il y a 2 Parcelles sur le Plateau