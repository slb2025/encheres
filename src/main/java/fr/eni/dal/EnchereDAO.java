package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;

public interface EnchereDAO {

    void create(ArticleVendu articleVendu);
    boolean noEnchere(int id);



}
