package fr.eni.bo;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleVendu {

    private int idArticle;
    private String nomArticle;
    private String description;
    private LocalDateTime dateDebutEncheres;
    private LocalDateTime dateFinEncheres;
    private int miseAPrix; // A vérifier
    private int prixVente;
    private String etatVente;

    private Categorie categorieArticle;
    private Utilisateur vendeur; // A vérifier
    private Retrait lieuRetrait;

    private List<Enchere> encheres;



}
