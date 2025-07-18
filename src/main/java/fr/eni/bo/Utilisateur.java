package fr.eni.bo;

import java.util.List;
import java.util.Objects;

public class Utilisateur {

    private int id;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String rue;
    private String codePostal;
    private String ville;
    private String motDePasse;
    private String nvMotDePasse;
    private int credit; //credits = points
    private boolean administrateur;

    private List<ArticleVendu> ArticleVendu;
    private List<Enchere> encheres;

    //constructeur

    public Utilisateur() {

    }

    public Utilisateur(int id, String pseudo, String nom, String prenom, String email, String tel, String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur, List<ArticleVendu> articleVendu, List<Enchere> encheres) {
        this.id = id;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
        ArticleVendu = articleVendu;
        this.encheres = encheres;
    }


    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNvMotDePasse() {
        return nvMotDePasse;
    }

    public void setNvMotDePasse(String nvMotDePasse) {
        this.nvMotDePasse = nvMotDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public List<ArticleVendu> getArticleVendu() {
        return ArticleVendu;
    }

    public void setArticleVendu(List<ArticleVendu> articleVendu) {
        ArticleVendu = articleVendu;
    }

    public List<Enchere> getEncheres() {
        return encheres;
    }

    public void setEncheres(List<Enchere> encheres) {
        this.encheres = encheres;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", nvMotDePasse='" + nvMotDePasse + '\'' +
                ", credit=" + credit +
                ", administrateur=" + administrateur +
                ", ArticleVendu=" + ArticleVendu +
                ", encheres=" + encheres +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id && credit == that.credit && administrateur == that.administrateur && Objects.equals(pseudo, that.pseudo) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(email, that.email) && Objects.equals(tel, that.tel) && Objects.equals(rue, that.rue) && Objects.equals(codePostal, that.codePostal) && Objects.equals(ville, that.ville) && Objects.equals(motDePasse, that.motDePasse) && Objects.equals(nvMotDePasse, that.nvMotDePasse) && Objects.equals(ArticleVendu, that.ArticleVendu) && Objects.equals(encheres, that.encheres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pseudo, nom, prenom, email, tel, rue, codePostal, ville, motDePasse, nvMotDePasse, credit, administrateur, ArticleVendu, encheres);
    }
}
