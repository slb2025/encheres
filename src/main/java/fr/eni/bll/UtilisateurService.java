package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.UtilisateurDAO;

import java.sql.SQLException;

public interface UtilisateurService {
    void creer(Utilisateur utilisateur);
}