INSERT INTO Utilisateur (pseudo, nom, prenom, email, tel, rue, codePostal, ville, motDePasse, credit, isAdmin, dateCreation)
VALUES
    ('jdupont', 'Dupont', 'Jean', 'j.dupont@mail.com', '0600000001', '1 rue A', '44000', 'Nantes', 'azerty1', 120, 0, '2025-06-01'),
    ('mlegrand', 'Legrand', 'Marie', 'm.legrand@mail.com', '0600000002', '2 rue B', '75000', 'Paris', 'azerty2', 200, 1, '2025-06-02'),
    ('cbernard', 'Bernard', 'Claire', 'c.bernard@mail.com', '0600000003', '3 rue C', '31000', 'Toulouse', 'azerty3', 150, 0, '2025-06-03'),
    ('psimon', 'Simon', 'Paul', 'p.simon@mail.com', '0600000004', '4 avenue D', '13000', 'Marseille', 'azerty4', 180, 0, '2025-06-04'),
    ('ldurand', 'Durand', 'Lucie', 'l.durand@mail.com', '0600000005', '5 rue E', '67000', 'Strasbourg', 'azerty5', 170, 0, '2025-06-05');

INSERT INTO Categorie (libelle)
VALUES
    ('Informatique'),
    ('Ameublement'),
    ('Vêtement'),
    ('Sports&Loisirs');

INSERT INTO Retrait (rue, codePostal, ville)
VALUES
    ('10 rue des Lilas', '44000', 'Nantes'),
    ('25 avenue Victor Hugo', '75000', 'Paris'),
    ('8 rue Sainte-Catherine', '33000', 'Bordeaux'),
    ('14 chemin des Prés', '31000', 'Toulouse'),
    ('77 rue Nationale', '59000', 'Lille');

INSERT INTO Article (
    idUtilisateur, idCategorie, nom, descriptionArticle,
    dateDebut, dateFin, miseAPrix, prixVente, etatVente, dateCreation
) VALUES

      (4, 4, 'Vélo VTC Décathlon', 'Taille M, bon état, pneus neufs', '2025-07-04', '2025-07-10', 180.00, NULL, 'EN_COURS', '2025-07-02'),
      (3, 1, 'Écran 24" Full HD', 'Dalle IPS, 75Hz, parfait pour le télétravail', '2025-07-04', '2025-07-10', 70.00, NULL, 'EN_COURS', '2025-07-02'),
      (2, 2, 'Canapé convertible 3 places', 'Tissu gris foncé, matelas intégré', '2025-07-04', '2025-07-10', 250.00, 280.00, 'TERMINEE', '2025-07-02'),
      (1, 1, 'Souris sans fil Logitech', 'Modèle ergonomique avec capteur optique 16000 DPI', '2025-07-04', '2025-07-10', 49.99, NULL, 'EN_COURS', '2025-07-02'),
      (2, 2, 'Table basse scandinave', 'Bois clair, design épuré 90x60cm', '2025-07-05', '2025-07-12', 80.00, 95.00, 'TERMINEE', '2025-07-02'),
      (3, 3, 'Raquette de tennis Babolat', 'Modèle Pure Drive 2023, manche 3', '2025-07-04', '2025-07-10', 49.99, NULL, 'EN_COURS', '2025-07-02'),
      (4, 1, 'Disque SSD NVMe 1To', 'Samsung 980 Pro, performances élevées', '2025-07-04', '2025-07-10', 49.99, NULL, 'EN_COURS', '2025-07-02'),
      (5, 2, 'Chaise de bureau pivotante', 'Hauteur réglable, noire, bon état', '2025-07-04', '2025-07-10', 49.99, NULL, 'EN_COURS', '2025-07-02'),
      (4, 3, 'Chemise Oxford bleu ciel', 'Taille M, coton doux, jamais portée', '2025-07-04', '2025-07-10', 49.99, NULL, 'EN_COURS', '2025-07-02');


INSERT INTO Enchere (idUtilisateur, idArticle, dateEnchere, montantEnchere)
VALUES
    (2, 32, '2025-07-02', 130),
    (3, 35, '2025-07-02', 55),
    (4, 43, '2025-07-03', 30),
    (1, 44, '2025-07-04', 420),
    (5, 52, '2025-07-05', 110),
    (2, 54, '2025-07-02', 200),
    (3, 35, '2025-07-02', 60),
    (4, 53, '2025-07-03', 50),
    (1, 44, '2025-07-04', 300),
    (5, 45, '2025-07-05', 140);

