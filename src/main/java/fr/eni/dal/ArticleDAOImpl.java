package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class ArticleDAOImpl implements ArticleDAO {

    private final String FIND_ARTICLE = "SELECT nom, miseAPrix, dateFinEncheres FROM ArticeVendu";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ArticleVendu> findAllArticle() {
        return jdbcTemplate.query(FIND_ARTICLE, new BeanPropertyRowMapper<>(ArticleVendu.class));
    }
}
