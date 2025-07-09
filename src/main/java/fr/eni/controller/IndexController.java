package fr.eni.controller;

import fr.eni.bll.ArticleService;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Utilisateur;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import fr.eni.bll.ArticleService;
import fr.eni.bo.ArticleVendu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

import java.util.List;

@Controller
@SessionAttributes("utilisateurConnecte")
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")

    public String AfficherAccueil(Model model, @ModelAttribute("utilisateurConnecte") Utilisateur utilisateur) {
        if (utilisateur == null) {
            return "redirect:/PageConnexion"; // Redirigez vers la page de connexion si non connecté
        }
        List<ArticleVendu> articleVendu = articleService.findAll();
        model.addAttribute("article", articleVendu);
        model.addAttribute("utilisateurConnecte", utilisateur);
        return "PagesListeEncheresConnecte";
    }


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
