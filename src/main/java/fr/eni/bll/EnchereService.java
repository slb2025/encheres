package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import jakarta.validation.Valid;

public interface EnchereService {

    static void creerVente(@Valid ArticleVendu articleVendu) {
    }
}
