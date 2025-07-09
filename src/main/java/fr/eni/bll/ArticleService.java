package fr.eni.bll;

import fr.eni.bo.ArticleVendu;

import java.util.List;

public interface ArticleService {

    List<ArticleVendu> getArticleAcceuilDeco();

    List<ArticleVendu> getArticlesParCategorie(String libelleCategorie, String nomArticle);
}
