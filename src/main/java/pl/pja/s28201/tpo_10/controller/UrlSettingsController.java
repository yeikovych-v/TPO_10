package pl.pja.s28201.tpo_10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pja.s28201.tpo_10.dto.UrlAuthDto;
import pl.pja.s28201.tpo_10.dto.UrlEditDto;
import pl.pja.s28201.tpo_10.dto.UrlObjectDto;
import pl.pja.s28201.tpo_10.repository.UrlObjectRepository;
import pl.pja.s28201.tpo_10.service.lang.GeneralLanguageService;

@Controller
@RequestMapping("/settings")
public class UrlSettingsController {

    private final UrlObjectRepository urlRepo;
    private final UrlRestController restController;
    private final GeneralLanguageService languageService;

    @Autowired
    public UrlSettingsController(UrlObjectRepository urlRepo, UrlRestController restController, GeneralLanguageService languageService) {
        this.urlRepo = urlRepo;
        this.languageService = languageService;
        this.restController = restController;
    }

    @GetMapping
    public String displaySettings(
            Model model,
            @ModelAttribute("authDto") UrlAuthDto dto,
            @ModelAttribute("badRequest") String badRequestMsg,
            @ModelAttribute("conflict") String conflictMsg
    ) {
        var urlOpt = urlRepo.findByNameAndPassword(dto.getName(), dto.getPassword());

        if (urlOpt.isEmpty())
            throw new IllegalArgumentException("Could not find url by name and password after authentication.");

        var url = urlOpt.get();
        var urlDto = new UrlEditDto();

        urlDto.setRedirectUrl(url.getRedirectUrl());
        urlDto.setTargetUrl(url.getTargetUrl());
        urlDto.setId(url.getId());
        urlDto.setName(url.getName());
        urlDto.setVisits(url.getVisits());

        model.addAttribute("urlDto", urlDto);
        model.addAttribute("uiDto", languageService.getSettingsDto());
        model.addAttribute("badRequest", badRequestMsg);
        model.addAttribute("conflict", conflictMsg);
        return "settings";
    }

    @PostMapping
    public String saveChangesAndRedirect(@ModelAttribute UrlEditDto dto, RedirectAttributes ra) {

        var auth = new UrlAuthDto();
        var dbUrl = urlRepo.findById(dto.getId());

        if (dbUrl.isEmpty()) {
            throw new IllegalArgumentException("Could not find url by id after edit.");
        }

        var url = dbUrl.get();

        var editedUrl = new UrlObjectDto();

        editedUrl.setName(dto.getName());
        editedUrl.setTargetUrl(dto.getTargetUrl());
        editedUrl.setPassword(url.getPassword());

        ResponseEntity<Object> response = restController.updateById(url.getId(), editedUrl);

        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            ra.addFlashAttribute("badRequest", languageService.getNotFoundErrorMessage());
        }

        if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
            ra.addFlashAttribute("invalidPassword", languageService.getInvalidPasswordErrorMessage());
            return "redirect:/auth";
        }

        if (response.getStatusCode() == HttpStatus.CONFLICT) {
            ra.addFlashAttribute("conflict", languageService.getUrlAlreadyExistsErrorMessage());
        }

        auth.setName(url.getName());
        auth.setPassword(url.getPassword());

        ra.addFlashAttribute("authDto", auth);

        return "redirect:/settings";
    }

    @PostMapping("/delete")
    public String deleteAndRedirect(@ModelAttribute UrlEditDto dto, RedirectAttributes ra) {
        var dbUrl = urlRepo.findById(dto.getId());

        if (dbUrl.isEmpty()) {
            throw new IllegalArgumentException("Could not find url by id after edit.");
        }

        var url = dbUrl.get();

        ResponseEntity<Object> response = restController.deleteById(url.getId(), url.getPassword());

        if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
            ra.addFlashAttribute("invalidPassword", languageService.getInvalidPasswordErrorMessage());
            return "redirect:/auth";
        }

        ra.addFlashAttribute("deletedSuccess", languageService.getLinkDeletedMessage());

        return "redirect:/";
    }

}
