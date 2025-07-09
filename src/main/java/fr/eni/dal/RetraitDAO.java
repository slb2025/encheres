package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Retrait;

public interface RetraitDAO {

    void createRetrait(ArticleVendu article);
    Retrait getRetraitByIdArticle(int idArticle);
}
