package fr.eni.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class utilisateurController {

    @PostMapping("/creer")
    public String creerFormateur(@Valid @ModelAttribute("formateur") Formateur formateur, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-formateur-creer";
        } else {
            try {
                utilisateurService.add(formateur);
                return "redirect:/formateurs";
            } catch (BusinessException e) {
                // Afficher les messages d’erreur - il faut les injecter dans le contexte de
                // BindingResult
                e.getClefsExternalisations().forEach(key -> {
                    ObjectError error = new ObjectError("globalError", key);
                    bindingResult.addError(error);
                });

                return "view-formateur-creer";
            }
        }
    }
}
