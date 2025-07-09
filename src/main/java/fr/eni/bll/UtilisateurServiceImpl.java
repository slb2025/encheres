package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Import manquant
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurDAO utilisateurDAO;
    private final ArticleService articleService;
    private final EnchereService enchereService;

    // Injection du PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, ArticleService articleService, EnchereService enchereService) {
        this.utilisateurDAO = utilisateurDAO; // Correction de la typo
        this.articleService = articleService;
        this.enchereService = enchereService;
    }

    @Override
    public void addUser(Utilisateur utilisateur) {
        // Hacher le mot de passe avant de sauvegarder
        String motDePasseHashe = passwordEncoder.encode(utilisateur.getMotDePasse());
        utilisateur.setMotDePasse(motDePasseHashe);

        utilisateurDAO.createUser(utilisateur);
    }

    @Override
    public boolean pseudoOuEmailExiste(String pseudo, String email) {
        boolean existe = utilisateurDAO.findByPseudo(pseudo) != null || utilisateurDAO.findByEmail(email) != null;
        System.out.println("Vérif si existe : " + existe);
        return existe;
    }

    @Override
    public Utilisateur login(String pseudo, String password) {
        // Recherche de l'utilisateur en base via DAO
        Utilisateur utilisateur = utilisateurDAO.findByPseudo(pseudo);

        if (utilisateur == null) {
            return null;
        }

        // Utiliser le PasswordEncoder pour vérifier le mot de passe hashé
        if (passwordEncoder.matches(password, utilisateur.getMotDePasse())) {
            return utilisateur;
        }
        return null;
    }

    @Override
    public Utilisateur afficherProfil(int id) {
        return utilisateurDAO.findById(id);
    }

    @Override
    public void modifierProfil(Utilisateur utilisateur) {
        // Si le mot de passe est modifié, le hacher
        if (utilisateur.getMotDePasse() != null && !utilisateur.getMotDePasse().isEmpty()) {
            String motDePasseHashe = passwordEncoder.encode(utilisateur.getMotDePasse());
            utilisateur.setMotDePasse(motDePasseHashe);
        }

        utilisateurDAO.modifierProfil(utilisateur);
    }

    @Override
    public boolean verifierMotDePasse(String pseudo, String motDePasse) {
        Utilisateur utilisateur = utilisateurDAO.findByPseudo(pseudo);
        if (utilisateur == null) {
            return false;
        }
        return passwordEncoder.matches(motDePasse, utilisateur.getMotDePasse());
    }

    @Override
    public boolean peutSupprimerCompte(int idUtilisateur) {
        if (articleService.verifUtilisateurProduit(idUtilisateur)) {
            return false;
        }
        if (enchereService.verifUtilisateurEnchere(idUtilisateur)) {
            return false;
        }
        return true;
    }

    @Override
    public void supprimerCompte(int idUtilisateur) {
        if (!peutSupprimerCompte(idUtilisateur)) {
            throw new IllegalStateException("Impossible de supprimer le compte : des enchères ou articles sont en cours");
        }
        utilisateurDAO.supprimerUtilisateur(idUtilisateur);
    }
}