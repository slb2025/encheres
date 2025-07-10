package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;

import java.util.List;

public interface ArticleDAO {

    List<ArticleVendu> findAllArticle();
    ArticleVendu getArticleById(int idArticle);
    boolean noArticle(int id);
    List<ArticleVendu> findArticleAccueilDeco();
    List<ArticleVendu> findArticleAccueilCon();
        List<ArticleVendu> findByCategorie(String libelleCategorie, String nomArticle);
}
