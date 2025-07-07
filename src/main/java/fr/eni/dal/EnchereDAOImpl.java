package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
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



    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

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
        namedParameters.addValue("idUtilisateur", articleVendu.getVendeur().getIdUtilisateur());
//        namedParameters.addValue("rue", articleVendu.getLieuRetrait().getRue());
//        namedParameters.addValue("codePostal", articleVendu.getLieuRetrait().getCodePostal());
//        namedParameters.addValue("ville", articleVendu.getLieuRetrait().getVille());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        /* -- Besoind d'une Génération d'une clé  pour la table enchère?
        if (keyHolder != null && keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            idEnchere.setId(keyHolder.getKey().longValue());

         */
    }
}

