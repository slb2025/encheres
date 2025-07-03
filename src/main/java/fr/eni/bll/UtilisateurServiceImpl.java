package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.UtilisateurDAO;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {


    private final UtilisateurDAO utilisateurDAO;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public void creer(Utilisateur utilisateur) {

    }

    @Override
    public boolean login(String pseudo, String password) {
        // Recherche de l'utilisateur en base via DAO
        Utilisateur utilisateur = utilisateurDAO.findByPseudo(pseudo);

        if (utilisateur == null) {
            // Pas d'utilisateur avec ce pseudo
            return false;
        }

        // Ici, comparer le password reçu avec celui stocké
        // ATTENTION EN CLAIR POUR LE MOMENT
        return utilisateur.getMotDePasse().equals(password);
    }

    //Ajout SLB
    @Override
    public Utilisateur afficherProfil(int id) {
        return utilisateurDAO.findById(id);
    }
    //Fin ajout SLB

}
