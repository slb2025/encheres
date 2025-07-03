package fr.eni.dal;

import fr.eni.bo.Utilisateur;

import java.sql.SQLException;

public interface UtilisateurDAO {
    boolean pseudoExiste(String pseudo) throws SQLException;
    boolean emailExiste(String email) throws SQLException;
    Utilisateur findByPseudo(String pseudo);
}
