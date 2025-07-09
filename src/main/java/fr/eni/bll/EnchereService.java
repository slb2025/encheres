package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;

import java.util.List;

public interface EnchereService {


    boolean verifUtilisateurEnchere(int id);
    void creerVente(ArticleVendu articleVendu) /*throws BusinessException*/;
    void creerEnchere(Utilisateur userSession, int montant, int idArticle);
}
