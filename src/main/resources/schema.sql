IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'Enchere')
BEGIN
    CREATE DATABASE Enchere;
END;
GO

USE Enchere;
GO

-- Table Utilisateur
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Utilisateur' AND xtype = 'U')
BEGIN
    CREATE TABLE Utilisateur (
        id INT PRIMARY KEY NOT NULL,
        pseudo VARCHAR(100) NOT NULL,
        nom VARCHAR(100) NOT NULL,
        prenom VARCHAR(100) NOT NULL,
        email VARCHAR(255) NOT NULL,
        tel VARCHAR(10) NOT NULL,
        rue VARCHAR(255) NOT NULL,
        codePostal VARCHAR(255) NOT NULL,
        ville VARCHAR(255) NOT NULL,
        motDePasse VARCHAR(255) NOT NULL,
        credit INT NOT NULL DEFAULT 100,
        isAdmin BIT NOT NULL DEFAULT 0
    );
END;
GO

-- Table Categorie
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Categorie' AND xtype = 'U')
BEGIN
    CREATE TABLE Categorie (
        id INT PRIMARY KEY NOT NULL,
        libelle VARCHAR(100) NOT NULL
    );
END;
GO

-- Table Article
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Article' AND xtype = 'U')
BEGIN
    CREATE TABLE Article (
        id INT PRIMARY KEY NOT NULL,
        idUtilisateur INT NOT NULL,
        idCategorie INT,
        nom VARCHAR(100) NOT NULL,
        descriptionArticle VARCHAR(255) NOT NULL,
        dateDebut DATE NOT NULL,
        dateFin DATE NOT NULL,
        miseAPrix INT NOT NULL,
        prixVente INT NOT NULL,
        etatVente VARCHAR(255),
        CONSTRAINT FK_Article_Utilisateur FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(id),
        CONSTRAINT FK_Article_Categorie FOREIGN KEY (idCategorie) REFERENCES Categorie(id)
    );
END;
GO

-- Table Enchere
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Enchere' AND xtype = 'U')
BEGIN
    CREATE TABLE Enchere (
        id INT PRIMARY KEY NOT NULL,
        idUtilisateur INT NOT NULL,
		idArticle INT NOT NULL,
        dateEnchere DATE NOT NULL,
        montantEnchere INT NOT NULL,
		CONSTRAINT FK_Enchere_Article FOREIGN KEY (idArticle) REFERENCES Article(id),
        CONSTRAINT FK_Enchere_Utilisateur FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(id)
    );
END;
GO

-- Table Retrait
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Retrait' AND xtype = 'U')
BEGIN
    CREATE TABLE Retrait (
        id INT PRIMARY KEY NOT NULL,
        rue VARCHAR(255) NOT NULL,
        codePostal VARCHAR(6) NOT NULL,
        ville VARCHAR(255) NOT NULL,
    );
END;
GO

-- Table RetraitArticle
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'RetraitArticle' AND xtype = 'U')
BEGIN
    CREATE TABLE RetraitArticle (
        id INT PRIMARY KEY NOT NULL,
        idArticle INT NOT NULL,
        idRetrait INT NOT NULL,
        CONSTRAINT FK_Retrait_Article FOREIGN KEY (idArticle) REFERENCES Article(id),
        CONSTRAINT FK_Article_Retrait FOREIGN KEY (idRetrait) REFERENCES Retrait(id)
    );
END;
GO