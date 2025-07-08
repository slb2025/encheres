package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;

import java.util.List;

public interface ArticleDAO {

    boolean noArticle(int id);
    List<ArticleVendu> findArticleAccueilDeco();


}
