package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;

import java.util.List;

public interface ArticleDAO {

    List<ArticleVendu> findAllArticle();
    boolean noArticle(int id);

}
