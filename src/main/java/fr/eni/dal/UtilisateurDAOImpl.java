package fr.eni.dal;

import fr.eni.bo.Utilisateur;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//Ajout SLB
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

    private static final String FIND_PSEUDO = "SELECT * FROM Utilisateur WHERE pseudo = :pseudo";
    private static final String CREATE_USER = "INSERT INTO UTILISATEUR (pseudo, nom, prenom, email, tel, rue, codePostal, ville, motDePasse) VALUES (:pseudo, :nom, :prenom, :email, :tel, :rue, :codePostal, :ville, :motDePasse)";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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



    //Ajout SLB pour Afficher Profil

    @Override
    public Utilisateur findById(int id) {
        String sql = "SELECT * FROM Utilisateur WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Utilisateur.class));

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Fin ajout SLB
}
