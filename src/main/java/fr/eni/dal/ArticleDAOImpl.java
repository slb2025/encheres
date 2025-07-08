package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

    private final String FIND_ARTICLE = "SELECT Article.nom, Article.miseAPrix, Article.dateFin, Utilisateur.pseudo FROM Article\n" +
        "JOIN Utilisateur ON Article.idUtilisateur = Utilisateur.id;\n";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public ArticleDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ArticleVendu> findArticleAccueilDeco() {
        return jdbcTemplate.query(FIND_ARTICLE, new ArticleRowMapper());
    }

    static class ArticleRowMapper implements RowMapper<ArticleVendu> {

        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArticleVendu article = new ArticleVendu();
            article.setNomArticle(rs.getString("nom"));
            article.setMiseAPrix(rs.getInt("miseAPrix"));
            article.setDateFinEncheres(rs.getTimestamp("dateFin").toLocalDateTime());

            Utilisateur vendeur = new Utilisateur();
            vendeur.setPseudo(rs.getString("pseudo"));
            article.setVendeur(vendeur);

            return article;
        }
    }
}
