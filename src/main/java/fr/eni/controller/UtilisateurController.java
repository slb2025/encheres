package fr.eni.controller;

import fr.eni.bll.UtilisateurService;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import fr.eni.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

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

    //Ajout SLB 02/07 et modifié le 03/07
    @GetMapping("/PageProfilUtilisateur/{id}")
    public String afficherProfilUtilisateur(@PathVariable int id, Model model) {
        Utilisateur utilisateur = utilisateurService.afficherProfil(id);

        if (utilisateur != null) {
            model.addAttribute("utilisateur", utilisateur);
            return "PageProfil";
        } else {
            return "redirect:/PagesListeEncheresMesVentes";
        }
    }
    //Fin ajout SLB

    @GetMapping("/PagesListeEncheresConnecte")
    public String afficherPagesListeEncheresConnecte() {
        return "PagesListeEncheresConnecte";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String pseudo,
            @RequestParam String password,
            Model model) {

        boolean success = utilisateurService.login(pseudo, password);

        if (success) {
            return "redirect:/PagesListeEncheresConnecte";
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
        this.utilisateurService.addUser(utilisateur);

        return "redirect:/PagesAcceuilNonConnecte";
    }


}
