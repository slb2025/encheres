package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.UtilisateurDAO;

import java.sql.SQLException;

public interface UtilisateurService {
    void addUser(Utilisateur utilisateur);

    boolean login(String pseudo, String password);

    boolean pseudoOuEmailExiste(String pseudo, String email);

}