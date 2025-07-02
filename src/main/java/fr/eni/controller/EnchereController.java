package fr.eni.controller;

import fr.eni.bll.EnchereService;
import fr.eni.bo.ArticleVendu;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnchereController {


    @Controller
    public class VenteController {

        @GetMapping("/creer")
        public String creerVente(Model model) {
            model.addAttribute("ArticleVendu", new ArticleVendu());
            return "PageEnchereNonCommencée";
        }

        @PostMapping("/creer")
        public String creerVente(@Valid @ModelAttribute("ArticleVendu") ArticleVendu articleVendu) {
            EnchereService.creerVente(articleVendu);
            return "redirect:/films";
        }
    }
}