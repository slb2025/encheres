package fr.eni.controller;

import fr.eni.bll.EnchereService;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes({"categories"})
@Controller
public class EnchereController {

    @Autowired
    private EnchereService enchereService;

    public EnchereController(EnchereService enchereService) {
        this.enchereService = enchereService;
    }

    @GetMapping("/VendreUnArticle")
    public String creerVente(Model model) {
        model.addAttribute("articleVendu", new ArticleVendu());

        return "PageVendreUnArticle";
    }

    @PostMapping("/creerEnchere")
    public String creerVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu) {
        System.out.println(articleVendu);
        enchereService.creerVente(articleVendu);
        return "redirect:/PageEnchereNonCommencee";
        //PagesListeEncheresConnecte.html
        //A rajouter @Valid

    }


    @ModelAttribute("categories")
    public List<Categorie> chargerCategorie() {
        System.out.println(chargerCategorie().stream().iterator());
        return enchereService.getAllCategories();
    }
}

