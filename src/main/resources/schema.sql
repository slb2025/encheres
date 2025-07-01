-- Création de la base de données

-- IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'Enchere')
--
-- CREATE DATABASE Enchere;
--
-- GO
--
-- USE Enchere;
--
-- GO
--
-- -- Table Utilisateur

DROP TABLE IF EXISTS RetraitArticle;
DROP TABLE IF EXISTS Retrait;
DROP TABLE IF EXISTS Enchere;
DROP TABLE IF EXISTS Article;
DROP TABLE IF EXISTS Categorie;
DROP TABLE IF EXISTS Utilisateur;

CREATE TABLE Utilisateur (

                             id INT IDENTITY(1,1) PRIMARY KEY,

                             pseudo VARCHAR(100) NOT NULL UNIQUE,

                             nom VARCHAR(100) NOT NULL,

                             prenom VARCHAR(100) NOT NULL,

                             email VARCHAR(255) NOT NULL UNIQUE,

                             tel VARCHAR(20) NOT NULL,

                             rue VARCHAR(255) NOT NULL,

                             codePostal VARCHAR(10) NOT NULL,

                             ville VARCHAR(255) NOT NULL,

                             motDePasse VARCHAR(255) NOT NULL,

                             credit DECIMAL(10,2) NOT NULL DEFAULT 100.00,

                             isAdmin BIT NOT NULL DEFAULT 0,

                             dateCreation DATETIME NOT NULL DEFAULT GETDATE(),

                             CONSTRAINT CK_Utilisateur_Credit CHECK (credit >= 0)

);



-- Table Categorie


CREATE TABLE Categorie (

                           id INT IDENTITY(1,1) PRIMARY KEY,

                           libelle VARCHAR(100) NOT NULL UNIQUE

);



-- Table Article


    CREATE TABLE Article (

                         id INT IDENTITY(1,1) PRIMARY KEY,

                         idUtilisateur INT NOT NULL,

                         idCategorie INT,

                         nom VARCHAR(100) NOT NULL,

                         descriptionArticle VARCHAR(1000) NOT NULL,

                         dateDebut DATETIME NOT NULL,

                         dateFin DATETIME NOT NULL,

                         miseAPrix DECIMAL(10,2) NOT NULL,

                         prixVente DECIMAL(10,2),

                         etatVente VARCHAR(50) DEFAULT 'EN_COURS',

                         dateCreation DATETIME NOT NULL DEFAULT GETDATE(),

                         CONSTRAINT FK_Article_Utilisateur FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(id),

                         CONSTRAINT FK_Article_Categorie FOREIGN KEY (idCategorie) REFERENCES Categorie(id),

                         CONSTRAINT CK_Article_Dates CHECK (dateFin > dateDebut),

                         CONSTRAINT CK_Article_Prix CHECK (miseAPrix > 0),

                         CONSTRAINT CK_Article_Etat CHECK (etatVente IN ('EN_COURS', 'TERMINEE', 'ANNULEE'))

);



-- Table Enchere



    CREATE TABLE Enchere (

                         id INT IDENTITY(1,1) PRIMARY KEY,

                         idUtilisateur INT NOT NULL,

                         idArticle INT NOT NULL,

                         dateEnchere DATETIME NOT NULL DEFAULT GETDATE(),

                         montantEnchere DECIMAL(10,2) NOT NULL,

                         CONSTRAINT FK_Enchere_Article FOREIGN KEY (idArticle) REFERENCES Article(id),

                         CONSTRAINT FK_Enchere_Utilisateur FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(id),

                         CONSTRAINT CK_Enchere_Montant CHECK (montantEnchere > 0)

);



-- Table Retrait



    CREATE TABLE Retrait (

                         id INT IDENTITY(1,1) PRIMARY KEY,

                         rue VARCHAR(255) NOT NULL,

                         codePostal VARCHAR(10) NOT NULL,

                         ville VARCHAR(255) NOT NULL

);



-- Table RetraitArticle


    CREATE TABLE RetraitArticle (

                                id INT IDENTITY(1,1) PRIMARY KEY,

                                idArticle INT NOT NULL,

                                idRetrait INT NOT NULL,

                                CONSTRAINT FK_RetraitArticle_Article FOREIGN KEY (idArticle) REFERENCES Article(id),

                                CONSTRAINT FK_RetraitArticle_Retrait FOREIGN KEY (idRetrait) REFERENCES Retrait(id),

                                CONSTRAINT UK_RetraitArticle UNIQUE (idArticle, idRetrait)

);


