package fr.eni.controller;
import fr.eni.bll.ArticleService;
import fr.eni.bll.CategorieService;
import fr.eni.bo.ArticleVendu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    private final CategorieService categorieService;
    private ArticleService articleService;


    public IndexController(ArticleService articleService, CategorieService categorieService) {
        this.articleService = articleService;
        this.categorieService = categorieService;
    }

    @GetMapping("/")
    public String AfficherAccueil(Model model) {
        System.out.println("Appel de la méthode index");

        List<ArticleVendu> article = articleService.getArticleAcceuilDeco();
        model.addAttribute("articleVendu", article);

        return "PagesAcceuilNonConnecte";
    }


    @PostMapping("/")
    public String filtrerParCategorie(
            @RequestParam(required = false) String categorie,
            @RequestParam(required = false) String nomArticle,
            Model model) {

        List<ArticleVendu> articles;

        if (categorie != null && !categorie.isEmpty() || nomArticle != null && !nomArticle.isEmpty()) {
            articles = articleService.getArticlesParCategorie(categorie, nomArticle);
        } else {
            articles = articleService.getArticleAcceuilDeco();
        }

        model.addAttribute("articleVendu", articles);
        model.addAttribute("categorieSelectionnee", categorie);
        model.addAttribute("nomArticle", nomArticle);

        return "PagesAcceuilNonConnecte";
    }


}
