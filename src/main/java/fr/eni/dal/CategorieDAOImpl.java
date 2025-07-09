package fr.eni.dal;

import fr.eni.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    @Override
    public List<Categorie> getCategories() {
        return namedParameterJdbcTemplate.query(
                SELECT_ALL,
                new BeanPropertyRowMapper<>(Categorie.class)
        );

    }

}
