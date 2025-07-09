package fr.eni.bo;

import java.time.LocalDateTime;

public class Enchere {

    private LocalDateTime dateEnchere;
    private long montantEnchere;

    private ArticleVendu articleVendu;
    private Utilisateur encherisseur;

    //constructeur

    public Enchere(){

    }

    public Enchere(LocalDateTime dateEnchere, long montantEnchere, ArticleVendu articleVendu, Utilisateur encherisseur) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.articleVendu = articleVendu;
        this.encherisseur = encherisseur;
    }

    //getters and setters

    public LocalDateTime getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDateTime dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public long getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(long montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }

    public Utilisateur getEncherisseur() {
        return encherisseur;
    }

    public void setEncherisseur(Utilisateur encherisseur) {
        this.encherisseur = encherisseur;
    }

}
