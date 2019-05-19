Feature: Poser une irrigation

  Scenario: Poser une irrigation
    Given Le plateau de départ contient 1 Parcelle
    When Je pose 2 parcelles
    Then Je pose l'irrigation sur les coordonées : <0.5 , 0.0 , -0.5>