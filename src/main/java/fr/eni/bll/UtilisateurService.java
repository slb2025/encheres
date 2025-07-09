package fr.eni.bll;

import fr.eni.bo.Utilisateur;

public interface UtilisateurService {
    void addUser(Utilisateur utilisateur);

    Utilisateur login(String pseudo, String password);

    //Ajout SLB :
    Utilisateur afficherProfil(int id);
    //Fin ajout SLB

    boolean pseudoOuEmailExiste(String pseudo, String email);

    boolean peutSupprimerCompte(int id);
    void supprimerCompte(int id);

    // Ajout SLB 07/07 :
    void modifierProfil(Utilisateur utilisateur);
    // Fin ajout SLB

    boolean verifierMotDePasse(String pseudo, String motDePasse);
}