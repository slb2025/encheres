package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereDAOImpl implements EnchereDAO {

    private final String INSERT = "INSERT INTO Article (idUtilisateur, nom, descriptionArticle, idCategorie, miseAPrix, dateDebut, dateFin)"
            + " VALUES (:idUtilisateur, :nomArticle, :description, :categorieArticle, :miseAprix, :dateDebutEncheres, :dateFinEncheres)";

    private final String CHECK_ENCHERES_EN_COURS = """
    SELECT COUNT(*) FROM Enchere e 
    INNER JOIN Article a ON e.idArticle = a.id 
    INNER JOIN Utilisateur u ON u.id = e.idUtilisateur
    WHERE e.idUtilisateur = :id
    AND a.dateFin > GETDATE()
    AND u.isDeleted = 0
    """;


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(ArticleVendu articleVendu) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nomArticle", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("categorieArticle", articleVendu.getCategorieArticle().getIdCategorie());
        namedParameters.addValue("miseAprix", articleVendu.getMiseAPrix());
        namedParameters.addValue("dateDebutEncheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("dateFinEncheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("idUtilisateur", "2");
        //namedParameters.addValue("idUtilisateur", articleVendu.getVendeur());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        /* -- Besoind d'une Génération d'une clé  pour la table enchère?
        if (keyHolder != null && keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            idEnchere.setId(keyHolder.getKey().longValue());

         */
    }
    @Override
    public boolean noEnchere(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            Integer count = jdbcTemplate.queryForObject(CHECK_ENCHERES_EN_COURS, params, Integer.class);
            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}

