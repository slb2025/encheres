package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.EnchereDAO;
import org.springframework.stereotype.Service;


@Service
public class EnchereServiceImpl  implements  EnchereService {


    private EnchereDAO enchereDAO;


    @Override
    public void creerVente(ArticleVendu articleVendu) {
        enchereDAO.create(articleVendu);
    }
}