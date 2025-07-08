package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ArticleDAOImpl implements ArticleDAO {

    private final String FIND_ARTICLE = "SELECT nom, miseAPrix, dateFinEncheres FROM ArticeVendu";
    private final String CHECK_ARTICLES_EN_COURS = "SELECT COUNT(*) FROM Article INNER JOIN Utilisateur ON Utilisateur.id = Article.idUtilisateur WHERE idUtilisateur = :id AND dateFin > GETDATE() AND Utilisateur.pseudo IS NOT NULL\n ";


    private NamedParameterJdbcTemplate jdbcTemplate;
    public ArticleDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ArticleVendu> findAllArticle() {
        return jdbcTemplate.query(FIND_ARTICLE, new BeanPropertyRowMapper<>(ArticleVendu.class));
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
