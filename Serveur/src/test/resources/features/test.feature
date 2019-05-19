Feature: the version can be retrieved
  Scenario: le serveur avant d appeler GET /nouvelle-connexion
    Given aucun client n est connecte
    When un client se connecte
    Then il y a 1 client