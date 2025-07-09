package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.ArticleDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO;

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public List<ArticleVendu> getArticleAcceuilDeco() {
        return articleDAO.findArticleAccueilDeco();
    }

    @Override
    public List<ArticleVendu> getArticlesParCategorie(String libelleCategorie, String nomArticle) {
        return articleDAO.findByCategorie(libelleCategorie, nomArticle);
    }
}
