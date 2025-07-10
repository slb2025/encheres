package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;
import org.springframework.dao.EmptyResultDataAccessException;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    private final String FIND_ARTICLE2 = "SELECT nom, miseAPrix, dateFinEncheres FROM ArticeVendu";
    private static final String SELECT_BY_ID = "SELECT idCategorie, prixVente, id, nom, descriptionArticle, dateDebut, dateFin, miseAPrix, prixVente, idUtilisateur FROM article WHERE id = :id";
    private final String FIND_ARTICLE = "SELECT Article.nom, Article.miseAPrix, Article.dateFin, Utilisateur.pseudo FROM Article\n" +
            "JOIN Utilisateur ON Article.idUtilisateur = Utilisateur.id;\n";



    private static final String FIND_BY_CATEGORIE =
            "SELECT a.nom, a.miseAPrix, a.dateFin, u.pseudo, c.libelle " +
                    "FROM Article a " +
                    "JOIN Utilisateur u ON a.idUtilisateur = u.id " +
                    "JOIN Categorie c ON a.idCategorie = c.id " +
                    "WHERE (:libelleCategorie IS NULL OR c.libelle = :libelleCategorie) " +
                    "AND (:nomArticle IS NULL OR a.nom LIKE '%' + :nomArticle + '%')";


    private final String CHECK_ARTICLES_EN_COURS = "SELECT COUNT(*) FROM Article INNER JOIN Utilisateur ON Utilisateur.id = Article.idUtilisateur WHERE idUtilisateur = :id AND Article.dateFin > GETDATE() AND Utilisateur.isDeleted = 0\n ";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public ArticleDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ArticleVendu> findArticleAccueilDeco() {
        return jdbcTemplate.query(FIND_ARTICLE, new ArticleRowMapper());
    }

    @Override
    public List<ArticleVendu> findByCategorie(String libelleCategorie, String nomArticle) {
        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("libelleCategorie", (libelleCategorie == null || libelleCategorie.isEmpty()) ? null : libelleCategorie);
        map.addValue("nomArticle", (nomArticle == null || nomArticle.isEmpty()) ? null : nomArticle);


        return jdbcTemplate.query(FIND_BY_CATEGORIE, map, new ArticleRowMapper());
    }
    @Override
    public List<ArticleVendu> findAllArticle() {
        return jdbcTemplate.query(FIND_ARTICLE2, new BeanPropertyRowMapper<>(ArticleVendu.class));
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


    static class ArticleRowMapper implements RowMapper<ArticleVendu> {

        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArticleVendu article = new ArticleVendu();
            article.setNomArticle(rs.getString("nom"));
            article.setMiseAPrix(rs.getInt("miseAPrix"));
            article.setDateFinEncheres(LocalDate.from(rs.getTimestamp("dateFin").toLocalDateTime()));

            Utilisateur vendeur = new Utilisateur();
            vendeur.setPseudo(rs.getString("pseudo"));
            article.setVendeur(vendeur);

            return article;
        }
    }

    @Override
    public boolean noArticle(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            Integer count = jdbcTemplate.queryForObject(CHECK_ARTICLES_EN_COURS, params, Integer.class);
            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}