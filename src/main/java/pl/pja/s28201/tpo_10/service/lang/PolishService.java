package pl.pja.s28201.tpo_10.service.lang;

import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.dto.LandingUiDto;

@Service
public class PolishService implements ILangService {
    @Override
    public String getLanguageCode() {
        return "PL";
    }

    @Override
    public LandingUiDto getLandingDto() {
        var landingDto = new LandingUiDto();

        landingDto.setChooseLanguageHeader("Wybierz język");
        landingDto.setEnglishButton("Angielski");
        landingDto.setPolishButton("Polski");
        landingDto.setGermanButton("Niemiecki");
        landingDto.setSubmitFormHeader("Prześlij formularz");

        landingDto.setNameLabel("Nazwa:");
        landingDto.setPasswordLabel("Hasło:");
        landingDto.setTargetUrlLabel("Docelowy URL:");
        landingDto.setButtonSubmit("Prześlij");
        landingDto.setButtonRedirect("Edytuj URL-e");

        return landingDto;
    }
}
