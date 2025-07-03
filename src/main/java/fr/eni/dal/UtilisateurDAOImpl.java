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
    private final DataSource dataSource;

    //constructor

    public UtilisateurDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void creer(Utilisateur utilisateur) throws SQLException {
        String sql = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, tel, rue, ville, motDePasse) VALUES (:pseudo, :nom, :prenom, :email, :tel, :rue, :ville, :motDePasse)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, utilisateur.getPseudo());
            stmt.setString(2, utilisateur.getEmail());
            stmt.setString(3, utilisateur.getMotDePasse());
            stmt.setInt(4, utilisateur.getCredit());
            stmt.executeUpdate();
        }
    }

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
