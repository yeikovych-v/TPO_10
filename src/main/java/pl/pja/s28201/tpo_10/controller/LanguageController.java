package pl.pja.s28201.tpo_10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pja.s28201.tpo_10.model.Language;
import pl.pja.s28201.tpo_10.service.lang.GeneralLanguageService;

@RestController
@RequestMapping("/lang")
public class LanguageController {

    private final GeneralLanguageService generalLanguageService;

    @Autowired
    public LanguageController(GeneralLanguageService generalLanguageService) {
        this.generalLanguageService = generalLanguageService;
    }

    @PostMapping("/{lang}")
    public String changeLanguage(@PathVariable String lang) {
        Language languageProfile = switch (lang) {
            case "pl" -> Language.PL;
            case "de" -> Language.DE;
            default -> Language.EN;
        };
        generalLanguageService.changeAppLanguage(languageProfile);
        return "redirect:/";
    }
}
