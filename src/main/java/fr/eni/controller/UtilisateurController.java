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


    @GetMapping("/PagesAcceuilNonConnecte")
    public String AfficherAccueilInscription() {
        return "PagesAcceuilNonConnecte";

    }


    // Création Créer Compte
    @GetMapping("/PageCreerCompte")
    public String afficherInscription(Model model) {
        model.addAttribute("utilisateur" , new Utilisateur());
        return "PageCreerCompte";
    }


    @PostMapping("/inscription")
    public String inscription(@ModelAttribute Utilisateur utilisateur, @RequestParam( name = "confirmation") String confirmation, Model model) {
        if (utilisateurService.pseudoOuEmailExiste(utilisateur.getPseudo(), utilisateur.getEmail())) {
            model.addAttribute("erreur", "Pseudo ou email déjà utilisé");
            return "PageCreerCompte";
        }

        if (!utilisateur.getMotDePasse().equals(confirmation)) {
            model.addAttribute("erreur", "Mot de passe incorrect");
            return "PageCreerCompte";
        }

        utilisateurService.addUser(utilisateur);
        return "redirect:/PagesListeEncheresConnecte";
    }

    @GetMapping({"/btnPageMonProfil", "/PageMonProfil/{id}"})
    public String afficherMonProfil(@PathVariable(required = false) Integer id, HttpSession session, Model model, RedirectAttributes redirectAttributes) {

        // Récupération de l'utilisateur connecté
        Utilisateur sessionUser = (Utilisateur) session.getAttribute("utilisateurConnecte");

        if (sessionUser == null) {
            redirectAttributes.addFlashAttribute("error", "Vous devez être connecté pour accéder à votre profil");
            return "redirect:/PageConnexion";
        }

        // Si pas d'ID dans l'URL, redirection vers l'URL avec ID
        if (id == null) {
            return "redirect:/PageMonProfil/" + sessionUser.getId();
        }

        // Affichage du profil
        model.addAttribute("utilisateur", sessionUser);
        return "PageMonProfil";
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
    @GetMapping("/supprimer-compte")
    public String deleteUser(@PathVariable(required = false) Integer id, HttpSession session, Model model, RedirectAttributes redirectAttribute) {
        // Vérifier que l'utilisateur est connecté
        Integer connectedUserId = getConnectedUserId(session);

        if (connectedUserId == null) {
            redirectAttribute.addFlashAttribute("error", "Vous devez être connecté pour supprimer votre profil");
            return "redirect:/PageConnexion";
        }

        // Vérifier que l'utilisateur supprime bien son propre profil (sécurité)
        if (!connectedUserId.equals(id)) {
            redirectAttribute.addFlashAttribute("error", "Vous ne pouvez supprimer que votre propre profil");
            return "redirect:/PagesListeEncheresConnecte";
        }

        // Récupérer les données de l'utilisateur
        Utilisateur utilisateur = utilisateurService.afficherProfil(id);

        if (utilisateur != null) {
            model.addAttribute("utilisateur", utilisateur);
            return "PageModifierProfil";
        } else {
            redirectAttribute.addFlashAttribute("error", "Utilisateur non trouvé");
            return "redirect:/PagesListeEncheresConnecte";
        }
    }

    //Ajout SLB 07/07 :
    @PostMapping("/btnModifierProfil/{id}")
    public String modifierProfil    (@PathVariable int id,
                                     @ModelAttribute Utilisateur utilisateurForm,
                                     @RequestParam String motDePasseActuel,
                                     @RequestParam String nvMotDePasse,
                                     @RequestParam String confirmation,
                                     HttpSession session,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {

        Utilisateur sessionUser = (Utilisateur) session.getAttribute("utilisateurConnecte");

        if (sessionUser == null || sessionUser.getId() != id) {
            redirectAttributes.addFlashAttribute("error", "Accès non autorisé.");
            return "redirect:/PageConnexion";
        }

        // Validation des champs obligatoires
        if (utilisateurForm.getPseudo() == null || utilisateurForm.getPseudo().trim().isEmpty()) {
            model.addAttribute("error", "Le pseudo est obligatoire");
            model.addAttribute("utilisateur", sessionUser);
            return "PageModifierProfil";
        }

        if (utilisateurForm.getNom() == null || utilisateurForm.getNom().trim().isEmpty()) {
            model.addAttribute("error", "Le nom est obligatoire");
            model.addAttribute("utilisateur", sessionUser);
            return "PageModifierProfil";
        }

        if (utilisateurForm.getPrenom() == null || utilisateurForm.getPrenom().trim().isEmpty()) {
            model.addAttribute("error", "Le prénom est obligatoire");
            model.addAttribute("utilisateur", sessionUser);
            return "PageModifierProfil";
        }

        if (utilisateurForm.getEmail() == null || utilisateurForm.getEmail().trim().isEmpty()) {
            model.addAttribute("error", "L'email est obligatoire");
            model.addAttribute("utilisateur", sessionUser);
            return "PageModifierProfil";
        }

        // Mise à jour des informations personnelles
        sessionUser.setPseudo(utilisateurForm.getPseudo().trim());
        sessionUser.setNom(utilisateurForm.getNom().trim());
        sessionUser.setPrenom(utilisateurForm.getPrenom().trim());
        sessionUser.setEmail(utilisateurForm.getEmail().trim());
        sessionUser.setTel(utilisateurForm.getTel() != null ? utilisateurForm.getTel().trim() : "");
        sessionUser.setRue(utilisateurForm.getRue() != null ? utilisateurForm.getRue().trim() : "");
        sessionUser.setCodePostal(utilisateurForm.getCodePostal() != null ? utilisateurForm.getCodePostal().trim() : "");
        sessionUser.setVille(utilisateurForm.getVille() != null ? utilisateurForm.getVille().trim() : "");

        // Gestion du changement de mot de passe (optionnel)
        boolean motDePasseModifie = false;
        if (nvMotDePasse != null && !nvMotDePasse.trim().isEmpty()) {
            // Vérification du mot de passe actuel
            if (motDePasseActuel == null || motDePasseActuel.trim().isEmpty()) {
                model.addAttribute("error", "Vous devez saisir votre mot de passe actuel pour le modifier");
                model.addAttribute("utilisateur", sessionUser);
                return "PageModifierProfil";
            }

            if (!sessionUser.getMotDePasse().equals(motDePasseActuel)) {
                model.addAttribute("error", "Mot de passe actuel incorrect");
                model.addAttribute("utilisateur", sessionUser);
                return "PageModifierProfil";
            }

            // Vérification de la confirmation
            if (confirmation == null || !nvMotDePasse.equals(confirmation)) {
                model.addAttribute("error", "La confirmation ne correspond pas au nouveau mot de passe");
                model.addAttribute("utilisateur", sessionUser);
                return "PageModifierProfil";
            }

            // Validation du nouveau mot de passe (optionnel)
            if (nvMotDePasse.length() < 6) {
                model.addAttribute("error", "Le nouveau mot de passe doit contenir au moins 6 caractères");
                model.addAttribute("utilisateur", sessionUser);
                return "PageModifierProfil";
            }

            sessionUser.setMotDePasse(nvMotDePasse);
            motDePasseModifie = true;
        }

        try {
            // Sauvegarde en base de données
            utilisateurService.modifierProfil(sessionUser);

            // Mise à jour de la session
            session.setAttribute("utilisateurConnecte", sessionUser);

            // Message de succès
            String message = "Profil modifié avec succès !";
            if (motDePasseModifie) {
                message += " Votre mot de passe a également été modifié.";
            }

            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/PageModifierProfil/" + id;

        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la modification du profil : " + e.getMessage());
            model.addAttribute("utilisateur", sessionUser);
            return "PageModifierProfil";
        }
    }

//Fin ajout SLB


}

