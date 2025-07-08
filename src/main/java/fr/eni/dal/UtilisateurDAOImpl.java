package fr.eni.dal;

import fr.eni.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//Ajout SLB
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

    private static final String FIND_PSEUDO = "SELECT * FROM UTILISATEUR WHERE pseudo = :pseudo";
    private static final String CREATE_USER = "INSERT INTO UTILISATEUR (pseudo, nom, prenom, email, tel, rue, codePostal, ville, motDePasse) VALUES (:pseudo, :nom, :prenom, :email, :tel, :rue, :codePostal, :ville, :motDePasse)";
    private static final String FIND_EMAIL = "SELECT * FROM UTILISATEUR WHERE email = :email";
    //Ajout SLB :
    String FIND_ID = "SELECT * FROM Utilisateur WHERE id = :id";
    //Ajout SLB :
    //Ajout SLB 07/07 :
    private static final String UPDATE_MDP =
            "UPDATE UTILISATEUR SET pseudo = :pseudo, nom = :nom, prenom = :prenom, " +
                    "email = :email, tel = :tel, rue = :rue, codePostal = :codePostal, ville = :ville, " +
                    "motDePasse = :motDePasse WHERE id = :id";

    //Fin ajout SLB

    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UtilisateurDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Utilisateur findByPseudo(String pseudo) {;
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("pseudo", pseudo);

        try {
            return namedParameterJdbcTemplate.queryForObject(FIND_PSEUDO, map, new BeanPropertyRowMapper<>(Utilisateur.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void createUser(Utilisateur utilisateur) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("pseudo", utilisateur.getPseudo());
        map.addValue("nom", utilisateur.getNom());
        map.addValue("prenom", utilisateur.getPrenom());
        map.addValue("email", utilisateur.getEmail());
        map.addValue("tel", utilisateur.getTel());
        map.addValue("rue", utilisateur.getRue());
        map.addValue("codePostal", utilisateur.getCodePostal());
        map.addValue("ville", utilisateur.getVille());
        map.addValue("motDePasse", utilisateur.getMotDePasse());
        this.namedParameterJdbcTemplate.update(CREATE_USER, map);
    }


    @Override
    public Utilisateur findByEmail(String email) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);

        try {
            return namedParameterJdbcTemplate.queryForObject(FIND_EMAIL, map, new BeanPropertyRowMapper<>(Utilisateur.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }



    //Ajout SLB pour Afficher Profil

    @Override
    public Utilisateur findById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            return namedParameterJdbcTemplate.queryForObject(FIND_ID, params, new BeanPropertyRowMapper<>(Utilisateur.class));

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Fin ajout SLB

    //Ajout SLB 07/07 :
    @Override
    public void modifierProfil(Utilisateur utilisateur) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pseudo", utilisateur.getPseudo());
        params.addValue("nom", utilisateur.getNom());
        params.addValue("prenom", utilisateur.getPrenom());
        params.addValue("email", utilisateur.getEmail());
        params.addValue("tel", utilisateur.getTel());
        params.addValue("rue", utilisateur.getRue());
        params.addValue("codePostal", utilisateur.getCodePostal());
        params.addValue("ville", utilisateur.getVille());
        params.addValue("id", utilisateur.getId());
        params.addValue("motDePasse", utilisateur.getMotDePasse());
        params.addValue("id", utilisateur.getId());

        namedParameterJdbcTemplate.update(UPDATE_MDP, params);
    }

}
