package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.EnchereDAO;
import fr.eni.dal.UtilisateurDAO;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {


    private final UtilisateurDAO utilisateurDAO;
    private final ArticleService articleService;
    private final EnchereService enchereService;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
        this.articleService = articleService;
        this.enchereService = enchereService;
    }

    @Override
    public void addUser(Utilisateur utilisateur) {
        utilisateurDAO.createUser(utilisateur);
    }



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
            // Pas d'utilisateur avec ce pseudo
            return null;
        }

        // Ici, comparer le password reçu avec celui stocké
        if (utilisateur.getMotDePasse().equals(password)) {
            return utilisateur;
        }
        return null;
    }

    //Ajout SLB
    @Override
    public Utilisateur afficherProfil(int id) {
        return utilisateurDAO.findById(id);
    }
    //Fin ajout SLB

    //Ajout SLB 07/07
    @Override
    public void modifierProfil(Utilisateur utilisateur) {
        utilisateurDAO.modifierProfil(utilisateur);
    }
//    @Override
//    public Utilisateur modifierMotDePasse(String pseudo, String password) {
//        // Recherche de l'utilisateur en base via DAO
//        Utilisateur utilisateur = utilisateurDAO.findByPseudo(pseudo);
//
//        if (utilisateur == null) {
//            // Pas d'utilisateur avec ce pseudo
//            return null;
//        }
//
//        // Ici, comparer le password reçu avec celui stocké
//        if (utilisateur.getMotDePasse().equals(password)) {
//            return utilisateur;
//        }
//        return null;
//    }
    // Fin ajout SLB

    @Override
    public boolean peutSupprimerCompte(int idUtilisateur) {
        // Vérifier qu'il n'y a pas d'articles en cours de vente
        if (articleService.verifUtilisateurProduit(idUtilisateur)) {
            return false;
        }

        // Vérifier qu'il n'y a pas d'enchères en cours
        if (enchereService.verifUtilisateurEnchere(idUtilisateur)) {
            return false;
        }

        return true;
    }

    @Override
    public void supprimerCompte(int idUtilisateur) {
        // Vérifier avant de supprimer
        if (!peutSupprimerCompte(idUtilisateur)) {
            throw new IllegalStateException("Impossible de supprimer le compte : des enchères ou articles sont en cours");
        }

        utilisateurDAO.supprimerUtilisateur(idUtilisateur);
    }

}
