package fr.eni.bll;

import fr.eni.bo.ArticleVendu;

import java.util.List;

public interface ArticleService {

    ArticleVendu getArticleById(int idArticle);
    List<ArticleVendu> findAll();
    List<ArticleVendu> getArticleAcceuilDeco();
    List<ArticleVendu> getArticleAcceuilConn();

    boolean verifUtilisateurProduit(int id);

    List<ArticleVendu> getArticlesParCategorie(String libelleCategorie, String nomArticle);
}
