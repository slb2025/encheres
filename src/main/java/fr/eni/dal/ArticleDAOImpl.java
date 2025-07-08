package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

    private final String FIND_ARTICLE = "SELECT nom, miseAPrix, dateFinEncheres FROM ArticeVendu";
    private static final String SELECT_BY_ID = "SELECT idCategorie, prixVente, id, nom, descriptionArticle, dateDebut, dateFin, miseAPrix, prixVente, idUtilisateur FROM article WHERE id = :id";
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ArticleVendu> findAllArticle() {
        return jdbcTemplate.query(FIND_ARTICLE, new BeanPropertyRowMapper<>(ArticleVendu.class));
    }

    @Override
    public ArticleVendu getArticleById(int idArticle) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", idArticle);
        return jdbcTemplate.queryForObject(SELECT_BY_ID, map, new ArticleVenduRowMapper());
    }

    class ArticleVenduRowMapper implements RowMapper<ArticleVendu> {
        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {

            ArticleVendu article = new ArticleVendu();
            article.setIdArticle(rs.getInt("id"));
            article.setNomArticle(rs.getString("nom"));
            article.setDescription(rs.getString("descriptionArticle"));
            article.setDateDebutEncheres(LocalDate.from(rs.getTimestamp("dateDebut").toLocalDateTime()));
            article.setDateFinEncheres(LocalDate.from(rs.getTimestamp("dateFin").toLocalDateTime()));
            article.setMiseAPrix(rs.getInt("miseAPrix"));
            article.setPrixVente(rs.getInt("prixVente"));

            // ajout utilisateur

            Utilisateur user = new Utilisateur();

            user.setId(rs.getInt("idUtilisateur"));
            article.setVendeur(user);

            // ajout categorie

            Categorie cat = new Categorie();
            cat.setIdCategorie(rs.getInt("idCategorie"));
            article.setCategorieArticle(cat);
            return article;
        }
    }
}

