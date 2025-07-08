package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Utilisateur;



public interface EnchereDAO {

    void create(ArticleVendu articleVendu);
    void creerEnchere(int montant, ArticleVendu article, Utilisateur userSession);
}
