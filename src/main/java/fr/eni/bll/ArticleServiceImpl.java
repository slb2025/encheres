package fr.eni.bll;

import fr.eni.dal.ArticleDAO;
import fr.eni.dal.EnchereDAO;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }
    @Override
    public boolean verifUtilisateurProduit(int id) {
        return articleDAO.noArticle(id);
    }
}
