package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.ArticleDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiveImpl implements ArticleService {

    private ArticleDAO articleDAO;

    public ArticleServiveImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public List<ArticleVendu> getArticleAcceuilDeco() {
        return articleDAO.findArticleAccueilDeco();
    }
}
