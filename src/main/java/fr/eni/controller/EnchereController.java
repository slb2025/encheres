package fr.eni.controller;

import fr.eni.bll.CategorieService;
import fr.eni.bll.EnchereService;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("userSession")
public class EnchereController {

    @Autowired
    private EnchereService enchereService;
    private CategorieService categorieService;

    public EnchereController(EnchereService enchereService, CategorieService categorieService) {
        this.enchereService = enchereService;
        this.categorieService = categorieService;
    }

    @GetMapping("/encheres/nouvelleVente")
    public String getCreerVente(Model model, @ModelAttribute("userSession") Utilisateur userSession) {
        ArticleVendu article = new ArticleVendu();
        Retrait retrait = new Retrait();

        article.setLieuRetrait(retrait);
        article.getLieuRetrait().setRue(userSession.getRue());
        article.getLieuRetrait().setCodePostal(userSession.getCodePostal());
        article.getLieuRetrait().setVille(userSession.getVille());
        model.addAttribute("articleVendu", article);

        List<Categorie> categories = categorieService.getCategories();
        model.addAttribute("categories", categories);
        System.out.println("création article");
        return "PageVendreUnArticle";
    }


    @PostMapping("encheres/creerEnchere")
    public String postCreerVente(@ModelAttribute("articleVendu") ArticleVendu article) {

        enchereService.creerVente(article);
        System.out.println("création article");
        //return "redirect:/encheres/enchereNonCommencee";
        return "PageEnchereNonCommencee";

    }

    @GetMapping("/encheres/enchereNonCommencee")
    public String enchereNonCommencee() {


        return "PageEnchereNonCommencee";
    }

  /*
  @PostMapping("encheres/creerEnchere")
public String postCreerVente(@ModelAttribute("articleVendu") ArticleVendu article) {
    ArticleVendu savedArticle = enchereService.creerVente(article);
    return "redirect:/encheres/" + savedArticle.getId();
}

@GetMapping("/encheres/{id}")
public String afficherEnchere(@PathVariable("id") Long id, Model model) {
    ArticleVendu article = enchereService.getArticleById(id);
    model.addAttribute("article", article);
    return "PageEnchereNonCommencee";
}
   */


/*
@PostMapping("encheres/creerEnchere")
public String postCreerVente(@Valid @ModelAttribute("articleVendu") ArticleVendu article,
                             BindingResult bindingResult, Model model, @ModelAttribute("userSession") Utilisateur userSession, @RequestParam("image") MultipartFile file) throws IOException {

    if (bindingResult.hasErrors()) {
        List<Categorie> categories = categorieService.getCategories();
        model.addAttribute("categories", categories);
        return "PageVendreUnArticle";
    } else {
        try {
            article.setVendeur(userSession);
            enchereService.creerVente(article);

            Path uploadDir = Paths.get("uploads/");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path imagePath = Paths.get("uploads/" + article.getIdArticle() + ".png");

            if (file.isEmpty()) {
                Path defaultImagePath = Paths.get("src/main/resources/static/images/photo.png");
                Files.copy(defaultImagePath, imagePath, StandardCopyOption.REPLACE_EXISTING);
            } else {
                byte[] bytes = file.getBytes();
                Files.write(imagePath, bytes);
            }

            return "redirect:/encheres";
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getClesErreurs().forEach(cle -> {
                ObjectError error = new ObjectError("globalError", cle);
                bindingResult.addError(error);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    List<Categorie> categories = categorieService.getCategories();
    model.addAttribute("categories", categories);
    return "PageEnchereNonCommencee";
}
*/
}

