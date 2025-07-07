package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.EnchereDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnchereServiceImpl implements EnchereService {

    @Autowired
    private EnchereDAO enchereDAO;

    @Override
    public void creerVente(ArticleVendu articleVendu) {
        enchereDAO.create(articleVendu);
    }
}
