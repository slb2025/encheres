package fr.eni.controller;

import fr.eni.bll.UtilisateurService;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
//Ajout SLB 03/07 pour que la pageListeenchereConnecte renvoie vers modifier le profil
import jakarta.servlet.http.HttpSession;
//Fin ajout
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    //Remplacement par SLB de :

//    @GetMapping("/PagesListeEncheresConnecte")
//    public String afficherPagesListeEncheresConnecte() {
//        return "PagesListeEncheresConnecte";
//    }

    //Par :
    @GetMapping("/PagesListeEncheresConnecte")
    public String afficherPagesListeEncheresConnecte(Model model, HttpSession session) {
        Utilisateur sessionUser = (Utilisateur) session.getAttribute("utilisateurConnecte");

//        if (sessionUser != null) {
//            Utilisateur utilisateur = utilisateurService.afficherProfil(sessionUser.getIdUtilisateur());
//            model.addAttribute("utilisateur", utilisateur);
//            return "PagesListeEncheresConnecte";
//        } else {
            return "PagesListeEncheresConnecte";
//        }
    }

    //Fin remplacement SLB

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

//    Ajout SLB 03/07
    @GetMapping("/modifier-profil/{id}")
    public String afficherFormulaireModification(@PathVariable("id") int id, Model model) {
        Utilisateur utilisateur = utilisateurService.afficherProfil(id);
        model.addAttribute("utilisateur", utilisateur);
        return "PageModifierProfil";
    }
// Fin Ajout SLB

}
