package fr.eni.controller;

import fr.eni.bll.UtilisateurService;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
//Ajout SLB 03/07 pour que la pageListeenchereConnecte renvoie vers modifier le profil
import jakarta.servlet.http.HttpSession;
//Fin ajout
import fr.eni.bo.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Méthode utilitaire pour récupérer l'ID de l'utilisateur connecté
     */
    private Integer getConnectedUserId(HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        System.out.println("Utilisateur en session : " + (utilisateur != null ? utilisateur.getPseudo() : "null"));
        System.out.println("Utilisateur en session : " + (utilisateur != null ? utilisateur.getId() : "null"));

        return utilisateur != null ? utilisateur.getId() : null;
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
    public String afficherPagesListeEncheresConnecte(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");

        if (utilisateur == null) {
            // Utilisateur non connecté, redirection vers la page de connexion
            redirectAttributes.addFlashAttribute("error", "Vous devez être connecté pour accéder à cette page");
            return "redirect:/";
        }

        // Utilisateur connecté, on peut ajouter ses infos au modèle si nécessaire
        model.addAttribute("utilisateur", utilisateur);
        return "PagesListeEncheresConnecte";
    }

    @PostMapping("/login")
    public String login(@RequestParam String pseudo, @RequestParam String password, HttpSession session, Model model) {

        Utilisateur utilisateur = utilisateurService.login(pseudo, password);

        if (utilisateur != null) {
            // Stocker l'utilisateur en session
            session.setAttribute("utilisateurConnecte", utilisateur);
            return "redirect:/PagesListeEncheresConnecte";
        } else {
            model.addAttribute("error", "Identifiant ou mot de passe incorrect.");
            return "PageConnexion";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session,RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "Tu es bien déconnecté");
        return "redirect:/";
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

    @GetMapping("/btnPageMonProfil")
    public String afficherMonProfil(HttpSession session, RedirectAttributes redirectAttributes) {
        Integer userId = getConnectedUserId(session);

        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "Vous devez être connecté pour accéder à votre profil");
            return "redirect:/PageConnexion";
        }
        return "redirect:/PageMonProfil/" + userId;
    }

    @GetMapping("/PageMonProfil/{id}")
    public String modifierProfilUtilisateur(Model model, HttpSession session) {
        Utilisateur sessionUser = (Utilisateur) session.getAttribute("utilisateurConnecte");
        if (sessionUser != null) {
            model.addAttribute("utilisateur", sessionUser);
            return "PageMonProfil";
        } else {
            model.addAttribute("error", "Identifiant ou mot de passe incorrect.");
            return "ListeEncheresConnecte";
        }
    }

//    Ajout SLB 03/07
    @GetMapping("/PageModifierProfil/{id}")
    public String afficherModifierProfil(@PathVariable int id, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Vérifier que l'utilisateur est connecté
        Integer connectedUserId = getConnectedUserId(session);

        if (connectedUserId == null) {
            redirectAttributes.addFlashAttribute("error", "Vous devez être connecté pour modifier votre profil");
            return "redirect:/PageConnexion";
        }

        // Vérifier que l'utilisateur modifie bien son propre profil (sécurité)
        if (!connectedUserId.equals(id)) {
            redirectAttributes.addFlashAttribute("error", "Vous ne pouvez modifier que votre propre profil");
            return "redirect:/PagesListeEncheresConnecte";
        }

        // Récupérer les données de l'utilisateur
        Utilisateur utilisateur = utilisateurService.afficherProfil(id);

        if (utilisateur != null) {
            model.addAttribute("utilisateur", utilisateur);
            return "PageModifierProfil";
        } else {
            redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé");
            return "redirect:/PagesListeEncheresConnecte";
        }
    }
// Fin Ajout SLB

}
