package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import fr.eni.bo.Categorie;

import java.util.List;

public interface EnchereDAO {

    void create(ArticleVendu articleVendu);
    boolean noEnchere(int id);



}
