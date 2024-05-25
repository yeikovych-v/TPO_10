package pl.pja.s28201.tpo_10.service.lang;

import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.dto.LandingUiDto;

@Service
public class EnglishService implements ILangService {
    @Override
    public String getLanguageCode() {
        return "EN";
    }

    @Override
    public LandingUiDto getLandingDto() {
        var landingDto = new LandingUiDto();

        landingDto.setChooseLanguageHeader("Choose Language");
        landingDto.setEnglishButton("English");
        landingDto.setPolishButton("Polish");
        landingDto.setGermanButton("German");
        landingDto.setSubmitFormHeader("Submit Form");

        landingDto.setNameLabel("Name:");
        landingDto.setPasswordLabel("Password:");
        landingDto.setTargetUrlLabel("Target URL:");
        landingDto.setButtonSubmit("Submit");
        landingDto.setButtonRedirect("Edit Urls");

        return landingDto;
    }

}
