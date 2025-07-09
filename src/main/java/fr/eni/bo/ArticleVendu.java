package fr.eni.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleVendu {

    private int idArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private int miseAPrix; // A vérifier
    private int prixVente;
    private String etatVente;

    private Categorie categorieArticle;
    private Utilisateur vendeur; // A vérifier
    private Retrait lieuRetrait;

    private List<Enchere> encheres;

    //constructeur


    public ArticleVendu(int idArticle) {
    }

    public ArticleVendu(int idArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Categorie categorieArticle, Utilisateur vendeur, Retrait lieuRetrait, List<Enchere> encheres) {
        this.idArticle = idArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        this.categorieArticle = categorieArticle;
        this.vendeur = vendeur;
        this.lieuRetrait = lieuRetrait;
        this.encheres = encheres;
    }

    public ArticleVendu() {

    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public void setEtatVente(String etatVente) {
        this.etatVente = etatVente;
    }

    public void setCategorieArticle(Categorie categorieArticle) {
        this.categorieArticle = categorieArticle;
    }

    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }

    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    public void setEncheres(List<Enchere> encheres) {
        this.encheres = encheres;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public String getEtatVente() {
        return etatVente;
    }

    public Categorie getCategorieArticle() {
        return categorieArticle;
    }

    public Utilisateur getVendeur() {
        return vendeur;
    }

    public Retrait getLieuRetrait() {
        return lieuRetrait;
    }

    public List<Enchere> getEncheres() {
        return encheres;
    }
}
//getters and setters

