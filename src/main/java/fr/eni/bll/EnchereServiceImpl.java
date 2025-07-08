package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.ArticleDAO;
import fr.eni.dal.EnchereDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnchereServiceImpl implements EnchereService {

    private final EnchereDAO enchereDAO;

    public EnchereServiceImpl(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
    }

    @Override
    public void creerVente(ArticleVendu articleVendu) {
        enchereDAO.create(articleVendu);
    }

    public boolean verifUtilisateurEnchere(int id) {
        return enchereDAO.noEnchere(id);
    }
}