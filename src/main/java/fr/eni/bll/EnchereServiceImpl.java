package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import org.springframework.stereotype.Service;

@Service
public class EnchereServiceImpl implements EnchereService {

    public void creerVente(ArticleVendu articleVendu) {

        EnchereDAO.create(/*film*/);

    }
}
