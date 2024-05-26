package pl.pja.s28201.tpo_10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pja.s28201.tpo_10.dto.UrlAuthDto;
import pl.pja.s28201.tpo_10.dto.UrlObjectDto;
import pl.pja.s28201.tpo_10.repository.UrlObjectRepository;
import pl.pja.s28201.tpo_10.service.lang.GeneralLanguageService;

@Controller
@RequestMapping("/auth")
public class UrlAuthController {

    private final UrlObjectRepository urlRepo;
    private final GeneralLanguageService languageService;

    @Autowired
    public UrlAuthController(UrlObjectRepository urlRepo, GeneralLanguageService languageService) {
        this.urlRepo = urlRepo;
        this.languageService = languageService;
    }

    @GetMapping
    public String displayUrlSettings(
            Model model,
            @ModelAttribute("invalidPassword") String invalidPassword
    ) {
        model.addAttribute("uiDto", languageService.getAuthDto());
        model.addAttribute("urlAuthDto", new UrlObjectDto());
        model.addAttribute("invalidPassword", invalidPassword);

        return "urlAuth";
    }

    @PostMapping String redirectOrError(@ModelAttribute UrlAuthDto dto, RedirectAttributes ra) {
        var urlOpt = urlRepo.findByNameAndPassword(dto.getName(), dto.getPassword());

        if (urlOpt.isEmpty()) {
            ra.addFlashAttribute("invalidPassword", languageService.getInvalidPasswordErrorMessage());
            return "redirect:/auth";
        }

        ra.addFlashAttribute("authDto", dto);
        return "redirect:/settings";
    }
}
