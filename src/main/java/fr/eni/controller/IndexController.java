package fr.eni.controller;
import fr.eni.bll.ArticleService;
import fr.eni.bo.ArticleVendu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private ArticleService articleService;


    public IndexController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String AfficherAccueil(Model model) {
        System.out.println("Appel de la méthode index");

        List<ArticleVendu> article = articleService.getArticleAcceuilDeco();
        model.addAttribute("articleVendu", article);

        return "PagesAcceuilNonConnecte";
    }


}
