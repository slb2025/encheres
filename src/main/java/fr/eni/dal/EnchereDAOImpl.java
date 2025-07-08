package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereDAOImpl implements EnchereDAO {

    private final String INSERT_ARTICLE = "INSERT INTO Article (idUtilisateur, nom, descriptionArticle, idCategorie, miseAPrix, dateDebut, dateFin)"
            + " VALUES (:idUtilisateur, :nomArticle, :description, :categorieArticle, :miseAprix, :dateDebutEncheres, :dateFinEncheres)";

    private static final String AJOUT_ENCHERE = "INSERT INTO ENCHERE (idUtilisateur, idArticle, dateEnchere, montantEnchere) VALUES (:userId, :idArticle, GETDATE(), :montant)";
    private static final String MAJ_PRIX_VENTE = "UPDATE ARTICLE SET prixVente = :prixVente WHERE id = :idArticle";


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void create(ArticleVendu articleVendu) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nomArticle", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        //namedParameters.addValue("categorieArticle", articleVendu.getCategorieArticle().getIdCategorie());
        namedParameters.addValue("categorieArticle", "1");
        namedParameters.addValue("miseAprix", articleVendu.getMiseAPrix());
        namedParameters.addValue("dateDebutEncheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("dateFinEncheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("idUtilisateur", "2");
        //namedParameters.addValue("idUtilisateur", articleVendu.getVendeur());

        jdbcTemplate.update(INSERT_ARTICLE, namedParameters, keyHolder);

    }

    @Override
    public void creerEnchere(int montant, ArticleVendu article, Utilisateur userSession) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("userId", userSession.getId());
        map.addValue("idArticle", article.getIdArticle());
        map.addValue("montant", montant);

        jdbcTemplate.update(AJOUT_ENCHERE, map);

        MapSqlParameterSource map2 = new MapSqlParameterSource();
        map2.addValue("prixVente", article.getPrixVente());
        map2.addValue("idArticle", article.getIdArticle());

        jdbcTemplate.update(MAJ_PRIX_VENTE, map2);
    }
}

