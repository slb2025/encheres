package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.ArticleDAO;
import fr.eni.dal.EnchereDAO;
import fr.eni.dal.RetraitDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnchereServiceImpl implements EnchereService {

    @Autowired
    private EnchereDAO enchereDAO;

    @Autowired
    private RetraitDAO retraitDAO;

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public void creerVente(ArticleVendu articleVendu) {

        enchereDAO.create(articleVendu);
        retraitDAO.createRetrait(articleVendu);
    }

    @Override
    public void creerEnchere(Utilisateur utilisateur, int montant, int idArticle) {

        ArticleVendu article = articleDAO.getArticleById(idArticle);

        article.setPrixVente(montant);
        enchereDAO.creerEnchere(montant, article, utilisateur);


    }

}
