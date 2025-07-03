package fr.eni.controller;

import fr.eni.bll.EnchereService;
import fr.eni.bo.ArticleVendu;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Clock;
import java.util.Collections;

@Controller
public class EnchereController {

    private EnchereService enchereService;

    public EnchereController(EnchereService enchereService) {
        this.enchereService = enchereService;
    }

    @GetMapping("/creer")
    public String creerVente(Model model) {
        model.addAttribute("articleVendu", new ArticleVendu());

        return "PageEnchereNonCommencée";
    }

    @PostMapping("/creerEnchere")
    public String creerVente(@ModelAttribute("articleVendu") ArticleVendu articleVendu) {
        System.out.println(articleVendu);
        enchereService.creerVente(articleVendu);
        return "redirect:/enchere";
        //PagesListeEncheresConnecte.html
        //A rajouter @Valid

    }
}

