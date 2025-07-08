package fr.eni.dal;

import fr.eni.bo.Categorie;

import java.util.List;

public interface CategorieDAO {

    List<Categorie> getCategories();
    Categorie getCategorie(int idCategorie);

}


