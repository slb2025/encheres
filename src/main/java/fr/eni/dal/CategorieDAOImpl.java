package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO {

        private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CategorieDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final String SELECT_ALL = "select id, libelle from categorie";
    private static final String SELECT_BY_ID = "select id, libelle from categorie where id = :idCategorie";


    @Override
    public List<Categorie> getCategories() {
        return namedParameterJdbcTemplate.query(
                SELECT_ALL,
                new BeanPropertyRowMapper<>(Categorie.class)
        );

    }

    @Override
    public Categorie getCategorie(int idCategorie) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("idCategorie", idCategorie);
        return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters,
                new BeanPropertyRowMapper<>(Categorie.class));
    }
    }
