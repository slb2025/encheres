package fr.eni.bll;


import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.dal.ArticleDAOImpl;
import fr.eni.dal.CategorieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    //private ArticleVenduDAO articleVenduDAO;
    private CategorieDAO categorieDAO;

    @Autowired
    public CategorieServiceImpl(CategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
    }


    @Override
    public List<Categorie> getCategories() {
        return categorieDAO.getCategories();
    }

}
