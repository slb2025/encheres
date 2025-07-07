package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.EnchereDAO;
import fr.eni.dal.RetraitDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnchereServiceImpl implements EnchereService {

    private final EnchereDAO enchereDAO;

    // Injection du DAO par constructeur
    public EnchereServiceImpl(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
    }

    @Autowired
    private EnchereDAO enchereDAO;

    @Autowired
    private RetraitDAO retraitDAO;

    @Override
    public void creerVente(ArticleVendu articleVendu) {

        enchereDAO.create(articleVendu);
        retraitDAO.createRetrait(articleVendu);
    }

    public boolean verifUtilisateurEnchere(int id) {
        return enchereDAO.noEnchere(id) != null;
    }

    public boolean verifUtilisateurProduit(int id) {
        return enchereDAO.noProduit(id) != null;
    }
}
