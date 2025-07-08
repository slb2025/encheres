package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.UtilisateurDAO;

import java.sql.SQLException;

public interface UtilisateurService {
    void addUser(Utilisateur utilisateur);

    Utilisateur login(String pseudo, String password);

    //Ajout SLB :
    Utilisateur afficherProfil(int id);
    //Fin ajout SLB

    boolean pseudoOuEmailExiste(String pseudo, String email);

    // Ajout SLB 07/07 :
    void modifierProfil(Utilisateur utilisateur);
    // Fin ajout SLB
}
