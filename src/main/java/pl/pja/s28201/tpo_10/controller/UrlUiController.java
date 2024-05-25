package pl.pja.s28201.tpo_10.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pja.s28201.tpo_10.dto.UrlObjectDto;
import pl.pja.s28201.tpo_10.service.lang.GeneralLanguageService;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class UrlUiController {

    private final GeneralLanguageService generalLanguageService;
    private final RestTemplate restTemplate;

    @Autowired
    public UrlUiController(GeneralLanguageService generalLanguageService, RestTemplate restTemplate) {
        this.generalLanguageService = generalLanguageService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String displayLandingPage(
            Model model,
            @ModelAttribute("successMsg") String successMsg,
            @ModelAttribute("errorsName") ArrayList<String> errorsName,
            @ModelAttribute("errorsPsw") ArrayList<String> errorsPsw,
            @ModelAttribute("errorsUrl") ArrayList<String> errorsUrl
    ) {
        model.addAttribute("dto", new UrlObjectDto());
        model.addAttribute("uiDto", generalLanguageService.getLandingDto());

        model.addAttribute("successMsg", successMsg);
        model.addAttribute("errorsName", errorsName);
        model.addAttribute("errorsPsw", errorsPsw);
        model.addAttribute("errorsUrl", errorsUrl);

        System.out.println("LL::"+ generalLanguageService.getLanguageCode());
        System.out.println(errorsName.toString());
        System.out.println(errorsPsw.toString());
        System.out.println(errorsUrl.toString());
        System.out.println(successMsg);
        System.out.println("---------------");
        return "landing";
    }

    @PostMapping
    public String returnUrlDto(@ModelAttribute @Valid UrlObjectDto dto, Errors errors, RedirectAttributes ra) {
        if (errors.hasFieldErrors("name")) {
            ra.addFlashAttribute("errorsName", errors.getFieldErrors("name").stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        if (errors.hasFieldErrors("password")) {
            ra.addFlashAttribute("errorsPsw", errors.getFieldErrors("password").stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        if (errors.hasFieldErrors("targetUrl")) {
            ra.addFlashAttribute("errorsUrl", errors.getFieldErrors("targetUrl").stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        if (errors.hasErrors()) {
            return "redirect:/";
        }

        ResponseEntity<Object> response = restTemplate.postForEntity("http://localhost:8080/api/links", dto, Object.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            ra.addFlashAttribute("successMsg", "Link was Successfully created.");
        }

        return "redirect:/";
    }


//    To Other Controller
    @GetMapping("/urlAuth")
    public String displayHelloPage(Model model) {

        return "urlAuth";
    }
}
