package fr.eni.dal;

import fr.eni.bo.ArticleVendu;

import java.util.List;

public interface ArticleDAO {

    List<ArticleVendu> findArticleAccueilDeco();

    List<ArticleVendu> findByCategorie(String libelleCategorie, String nomArticle);

}
