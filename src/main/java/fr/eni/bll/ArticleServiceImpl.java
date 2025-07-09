package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.ArticleDAO;
import fr.eni.dal.CategorieDAO;
import fr.eni.dal.RetraitDAO;
import fr.eni.dal.UtilisateurDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO;
    private UtilisateurDAO utilisateurDAO;
    private RetraitDAO retraitDAO;
    private CategorieDAO categorieDAO;


    // Récupération des information pour injection dans le modèle : Créer enchère
    @Override
    public ArticleVendu getArticleById(int idArticle) {

        ArticleVendu article = this.articleDAO.getArticleById(idArticle);
        Utilisateur vendeur = this.utilisateurDAO.findById(article.getVendeur().getId());
        Retrait lieuRetrait = this.retraitDAO.getRetraitByIdArticle(idArticle);
        String libelle = this.categorieDAO.getCategorie(article.getCategorieArticle().getIdCategorie())
                .getLibelle();
        article.setLieuRetrait(lieuRetrait);
        article.getCategorieArticle().setLibelle(libelle);
        article.setVendeur(vendeur);
        return article;
    }

    @Override
    public List<ArticleVendu> findAll() {
        return articleDAO.findAllArticle();
    }
}