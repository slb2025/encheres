package fr.eni.controller;

import fr.eni.bll.UtilisateurService;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/enchere")
    public String afficherEnchere(Model model) {
        Enchere nouvelleEnchere = new Enchere();
        model.addAttribute("enchere",nouvelleEnchere);

        return "PagesListeEncheresMesVentes";
    }

    @GetMapping("/PageConnexion")
    public String afficherLogin() {
        return "PageConnexion";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String pseudo,
            @RequestParam String password,
            Model model) {

        boolean success = utilisateurService.login(pseudo, password);

        if (success) {
            return "redirect:/PageProfil";
        } else {
            model.addAttribute("error", "Identifiant ou mot de passe incorrect.");
            return "PageConnexion";
        }
    }
    // Création Créer Compte
    @GetMapping("/PageCreerCompte")
    public String afficherInscription(Model model) {
        model.addAttribute("utilisateur" , new Utilisateur());
        return "PageCreerCompte";
    }

    @PostMapping("/inscription")
    public String inscription(@ModelAttribute Utilisateur utilisateur) {
        this.utilisateurService.creer(utilisateur);

        return "redirect:/PagesAcceuilNonConnecte";
    }
}
