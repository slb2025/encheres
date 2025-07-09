package fr.eni.bll;

import fr.eni.bo.ArticleVendu;

import java.util.List;

public interface ArticleService {

    List<ArticleVendu> getArticleAcceuilDeco();

    boolean verifUtilisateurProduit(int id);
}
