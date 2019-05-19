Feature: Poser Panda

  Scenario: deplacer le panda
    Given La parcelle posée a 1 bambou
    When Je déplace le panda vers la parcelle
    Then Il y a 0 bambou sur la parcelle