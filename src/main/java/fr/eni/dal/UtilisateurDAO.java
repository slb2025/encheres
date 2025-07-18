package fr.eni.dal;

import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;

import java.sql.SQLException;

public interface UtilisateurDAO {

    Utilisateur findByPseudo(String pseudo);

    void createUser(Utilisateur utilisateur);

    public Utilisateur findByEmail(String email);

    //Ajout SLB :
    Utilisateur findById(int Id);
    //Fin ajout SLB

    void supprimerUtilisateur(int id);

    //Ajout SLB 07/07 :
    void modifierProfil(Utilisateur utilisateur);
    //Fin ajout SLB
}
