Feature: Poser Jardinier

  Scenario: deplacer le jardinier
    Given La parcelle posée contient 1 bambou
    When Je déplace le jardinier vers la parcelle
    Then Il y a 2 bambous sur la parcelle posée