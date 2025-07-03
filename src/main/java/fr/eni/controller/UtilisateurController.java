package fr.eni.controller;

import fr.eni.bll.UtilisateurService;
import fr.eni.bo.Enchere;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;


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

    @GetMapping("/PagesListeEncheresConnecte")
    public String afficherPagesListeEncheresConnecte(HttpSession session) {
        if (session.getAttribute("pseudo") == null) {
            return "redirect:/PageConnexion";
        }
        return "PagesListeEncheresConnecte";
    }

    @PostMapping("/login")
    public String login(@RequestParam String pseudo,
                        @RequestParam String password,
                        @RequestParam(required = false) String souvenir,
                        HttpSession session,
                        HttpServletResponse response, // POUR AJOUTER UN COOKIE
                        Model model) {
        boolean success = utilisateurService.login(pseudo, password);

        if (success) {
            if ("on".equals(souvenir)) {
                Cookie cookie = new Cookie("pseudo", pseudo);
                cookie.setMaxAge(7 * 24 * 60 * 60); // 7 jours
                response.addCookie(cookie);
            }
            session.setAttribute("pseudo", pseudo);
            return "redirect:/PagesListeEncheresConnecte";
        } else {
            model.addAttribute("error", "Identifiant ou mot de passe incorrect.");
            return "PageConnexion";
        }
    }

}
