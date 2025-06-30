package fr.eni.bo;

import java.util.List;

public class Utilisateur {

    private int idUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String codePostal;
    private String ville;
    private String motDePasse;
    private int credit; //credits = points
    private boolean administrateur;

    private List<ArticleVendu> ArticleVendu;
    private List<Enchere> encheres;
}
