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

}
