package fr.eni.controller;

import fr.eni.bll.ArticleService;
import fr.eni.bll.CategorieService;
import fr.eni.bll.EnchereService;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@SessionAttributes("utilisateurConnecte")
public class EnchereController {

    @Autowired
    private EnchereService enchereService;
    private CategorieService categorieService;
    private ArticleService articleService;

    public EnchereController(EnchereService enchereService, CategorieService categorieService, ArticleService articleService) {
        this.enchereService = enchereService;
        this.categorieService = categorieService;
        this.articleService = articleService;
    }

    @GetMapping("/annulation")
    public String annulation() {
        return "PagesListeEncheresConnecte"; // nom de la vue à afficher
    }

    @GetMapping("encheres/creerEnchere")
    public String getCreerVente(Model model, @ModelAttribute("utilisateurConnecte") Utilisateur userSession) {

        ArticleVendu article = new ArticleVendu();
        Retrait retrait = new Retrait();

        article.setLieuRetrait(retrait);
        article.getLieuRetrait().setRue(userSession.getRue());
        article.getLieuRetrait().setCodePostal(userSession.getCodePostal());
        article.getLieuRetrait().setVille(userSession.getVille());
        model.addAttribute("articleVendu", article);

        List<Categorie> categories = categorieService.getCategories();
        model.addAttribute("categories", categories);
        System.out.println("création article");
        return "PageVendreUnArticle";
    }


    @PostMapping("encheres/creerEnchere")
    public String postCreerVente(@ModelAttribute("articleVendu") ArticleVendu article) {

        enchereService.creerVente(article);
        return "PagesListeEncheresConnecte.html";
    }

    @GetMapping("/encheres/enchereNonCommencee")
    public String enchereNonCommencee() {
        return "PageEnchereNonCommencee";
    }

    @GetMapping("/encheres/detail")
    public String detailArticle(@RequestParam("id") int id, Model model,
                                @ModelAttribute("utilisateurConnecte") Utilisateur utilisateur) {


        ArticleVendu article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        model.addAttribute("utilisateurConnecte", utilisateur);

        return "PageEncherir";
    }

    @PostMapping("enchere/créer")

    public String postCreerEnchere(@RequestParam(name = "montant") int montant, @RequestParam("id") int idArticle,
                                   @ModelAttribute("userSession") Utilisateur utilisateur) {

        this.enchereService.creerEnchere(utilisateur, montant, idArticle);
        return "redirect:/encheres/detail?id=" + idArticle;

    }
}


/*
@GetMapping("/encheres/{id}")
public String afficherEnchere(@PathVariable("id") Long id, Model model) {
    ArticleVendu article = enchereService.getArticleById(id);
    model.addAttribute("article", article);
    return "PageEnchereNonCommencee";
}
   */







