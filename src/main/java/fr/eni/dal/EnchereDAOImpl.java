package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import org.springframework.beans.factory.annotation.Autowired;
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
}

