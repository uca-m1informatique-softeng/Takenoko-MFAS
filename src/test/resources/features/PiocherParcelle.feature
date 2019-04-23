Feature: Piocher Parcelle

  Scenario: Piocher une parcelle
    Given Un deck de départ avec 27 Parcelle
    When Je pioche une Parcelle
    Then Il y a 26 Parcelles dans le deck