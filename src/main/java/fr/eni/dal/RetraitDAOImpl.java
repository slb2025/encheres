package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public class RetraitDAOImpl implements RetraitDAO {

    private static final String INSERT = "insert into retrait (rue, codePostal, ville) values (:rue, :codePostal, :ville)";

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
}

