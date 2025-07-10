package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RetraitDAOImpl implements RetraitDAO {

    private static final String INSERT = "insert into retrait (rue, codePostal, ville) values (:rue, :codePostal, :ville)";
    private static final String SELECT_BY_ID = "SELECT * FROM retrait WHERE id = :idArticle";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createRetrait(ArticleVendu article) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idArticle", article.getIdArticle());
        map.addValue("rue", article.getLieuRetrait().getRue());
        map.addValue("codePostal", article.getLieuRetrait().getCodePostal());
        map.addValue("ville", article.getLieuRetrait().getVille());

        namedParameterJdbcTemplate.update(INSERT, map);

    }

    public Retrait getRetraitByIdArticle(int idArticle) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idArticle", idArticle);  // corriger ici : "idArticle" avec A majuscule
        return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, map, new BeanPropertyRowMapper<>(Retrait.class));
    }

}
