package pl.pja.s28201.tpo_10.service.lang;

import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.dto.LandingUiDto;

@Service
public class GermanService implements ILangService {
    @Override
    public String getLanguageCode() {
        return "DE";
    }

    @Override
    public LandingUiDto getLandingDto() {
        var landingDto = new LandingUiDto();

        landingDto.setChooseLanguageHeader("Sprache wählen");
        landingDto.setEnglishButton("Englisch");
        landingDto.setPolishButton("Polnisch");
        landingDto.setGermanButton("Deutsch");
        landingDto.setSubmitFormHeader("Formular absenden");

        landingDto.setNameLabel("Name:");
        landingDto.setPasswordLabel("Passwort:");
        landingDto.setTargetUrlLabel("Ziel-URL:");
        landingDto.setButtonSubmit("Absenden");
        landingDto.setButtonRedirect("URLs bearbeiten");

        return landingDto;
    }
}
