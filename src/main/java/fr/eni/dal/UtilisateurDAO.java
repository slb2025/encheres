package fr.eni.dal;

import fr.eni.bo.Utilisateur;

import java.sql.SQLException;

public interface UtilisateurDAO {

    Utilisateur findByPseudo(String pseudo);

    void createUser(Utilisateur utilisateur);

    public Utilisateur findByEmail(String email);
}
