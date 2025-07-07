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

@Repository
public class EnchereDAOImpl implements EnchereDAO {

    private final String INSERT = "INSERT INTO FILM(TITRE, ANNEE, DUREE, SYNOPSIS, ID_REALISATEUR, ID_GENRE) "
            + " VALUES (:titre, :annee, :duree, :synopsis, :idRealisateur, :idGenre)";


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(ArticleVendu articleVendu) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("article", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("catégorie", articleVendu.getCategorieArticle());
        namedParameters.addValue("miseAprix", articleVendu.getMiseAPrix());
        namedParameters.addValue("debutEnchere", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("finEnchere", articleVendu.getDateFinEncheres());
        namedParameters.addValue("rue", articleVendu.getLieuRetrait().getRue());
        namedParameters.addValue("codePostal", articleVendu.getLieuRetrait().getCodePostal());
        namedParameters.addValue("ville", articleVendu.getLieuRetrait().getVille());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        /* -- Besoind d'une Génération d'une clé  pour la table enchère?
        if (keyHolder != null && keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            idEnchere.setId(keyHolder.getKey().longValue());

         */
    }

    @Override
    public Enchere noEnchere(int id) {
        String sql = "SELECT * FROM Enchere INNER JOIN Utilisateur ON Utilisateur.id = Enchere.idUtilisateur WHERE Enchere.idUtilisateur = :id AND Utilisateur.pseudo IS NOT NULL\n";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Enchere.class));

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

