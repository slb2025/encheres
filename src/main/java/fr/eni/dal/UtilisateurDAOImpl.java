package fr.eni.dal;

import fr.eni.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//Ajout SLB
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

    private static final String FIND_PSEUDO = "SELECT * FROM Utilisateur WHERE pseudo = :pseudo";
    private static final String CREATE_USER = "INSERT INTO UTILISATEUR (pseudo, nom, prenom, email, tel, rue, codePostal, ville, motDePasse) VALUES (:pseudo, :nom, :prenom, :email, :tel, :rue, :codePostal, :ville, :motDePasse)";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UtilisateurDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        String sql = "SELECT * FROM utilisateurs WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setIdUtilisateur(rs.getInt("id"));
                u.setPseudo(rs.getString("pseudo"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setTelephone(rs.getString("telephone"));
                u.setRue(rs.getString("rue"));
                u.setCodePostal(rs.getString("code_postal"));
                u.setVille(rs.getString("ville"));
                return u;
            } else {
                throw new SQLException("Aucun utilisateur trouvé avec l'id : " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Fin ajout SLB
}
