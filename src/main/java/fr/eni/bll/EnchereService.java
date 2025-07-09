package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;

import java.util.List;

public interface EnchereService {

    void creerVente(ArticleVendu articleVendu);
    boolean verifUtilisateurEnchere(int id);


    void creerEnchere(Utilisateur userSession, int montant, int idArticle);
}
