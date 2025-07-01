package fr.eni.bo;

import java.util.List;

public class Categorie {

    private int idCategorie;
    private String libelle;

    private List<ArticleVendu> ArticleVendu;

    //Constructeur

    public Categorie(int idCategorie, String libelle, List<ArticleVendu> articleVendu) {
        this.idCategorie = idCategorie;
        this.libelle = libelle;
        ArticleVendu = articleVendu;
    }

    //getters and setters

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<ArticleVendu> getArticleVendu() {
        return ArticleVendu;
    }

    public void setArticleVendu(List<ArticleVendu> articleVendu) {
        ArticleVendu = articleVendu;
    }

}
