package fr.eni.dal;

import fr.eni.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {


    // Injection via constructeur
    public UtilisateurDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean pseudoExiste(String pseudo) throws SQLException {
//        String sql = "SELECT COUNT(*) FROM utilisateurs WHERE pseudo = ?";
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, pseudo);
//            ResultSet rs = stmt.executeQuery();
//            rs.next();
//            return rs.getInt(1) > 0;
//        }
        return  false;
    }

    @Override
    public boolean emailExiste(String email) throws SQLException {
//        String sql = "SELECT COUNT(*) FROM utilisateurs WHERE email = ?";
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, email);
//            ResultSet rs = stmt.executeQuery();
//            rs.next();
//            return rs.getInt(1) > 0;
//        }
        return  false;
    }

    @Override
    public Utilisateur findByPseudo(String pseudo) {
        String sql = "SELECT * FROM Utilisateur WHERE pseudo = :pseudo";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pseudo", pseudo);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Utilisateur.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
