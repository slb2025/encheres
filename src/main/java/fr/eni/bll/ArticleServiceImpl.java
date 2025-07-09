package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.ArticleDAO;
import fr.eni.dal.EnchereDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public List<ArticleVendu> getArticleAcceuilDeco() {
        return articleDAO.findArticleAccueilDeco();
    }

    @Override
    public boolean verifUtilisateurProduit(int id) {
        return articleDAO.noArticle(id);
    }
}
