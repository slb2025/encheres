package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
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

    private static final String AJOUT_ENCHERE = "INSERT INTO ENCHERE (idUtilisateur, idArticle, dateEnchere, montantEnchere) VALUES (:idUtilisateur, :idArticle, GETDATE(), :montant)";
    private static final String MAJ_PRIX_VENTE = "UPDATE ARTICLE SET prixVente = :prixVente WHERE id = :idArticle";


    private final String CHECK_ENCHERES_EN_COURS = """
    SELECT COUNT(*) FROM Enchere e 
    INNER JOIN Article a ON e.idArticle = a.id 
    INNER JOIN Utilisateur u ON u.id = e.idUtilisateur
    WHERE e.idUtilisateur = :id
    AND a.dateFin > GETDATE()
    AND u.isDeleted = 0
    """;


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(ArticleVendu articleVendu) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nomArticle", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("categorieArticle", articleVendu.getCategorieArticle().getIdCategorie());
        namedParameters.addValue("miseAprix", articleVendu.getMiseAPrix());
        namedParameters.addValue("dateDebutEncheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("dateFinEncheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("idUtilisateur", "2");
        //namedParameters.addValue("idUtilisateur", articleVendu.getVendeur());

        jdbcTemplate.update(INSERT_ARTICLE, namedParameters, keyHolder);

    }

    @Override
    public void creerEnchere(int montant, ArticleVendu article, Utilisateur utilisateur) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idUtilisateur", "6");
        map.addValue("idArticle", article.getIdArticle());
        map.addValue("montant", montant);

        jdbcTemplate.update(AJOUT_ENCHERE, map);

        MapSqlParameterSource map2 = new MapSqlParameterSource();
        map2.addValue("prixVente", article.getPrixVente());
        map2.addValue("idArticle", article.getIdArticle());

        jdbcTemplate.update(MAJ_PRIX_VENTE, map2);
    }
    @Override
    public boolean noEnchere(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            Integer count = jdbcTemplate.queryForObject(CHECK_ENCHERES_EN_COURS, params, Integer.class);
            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    /*
    class EnchèreRowMapper implements RowMapper<Enchere> {

        /**
         * Méthode rowmapper d'enchère, ajoutant un utilisateur avec un id et un article
         * avec un id
         */
    /*
        @Override
        public Enchère mapRow(ResultSet rs, int rowNum) throws SQLException {

            // implementation enchère

            Enchère e = new Enchère();
            e.setMontant_enchere(rs.getInt("MONTANT_ENCHERE"));
            e.setDateEnchère(rs.getTimestamp("DATE_ENCHERE").toLocalDateTime());

            // implementation utilisateur

            Utilisateur u = new Utilisateur();
            u.setNoUtilisateur(rs.getInt("NO_UTILISATEUR"));
            e.setUtilisateur(u);

            // implementation article

            ArticleVendu a = new ArticleVendu();
            a.setNoArticle(rs.getInt("NO_ARTICLE"));
            e.setArtcicleVendu(a);

            return e;
        }
        */
}

