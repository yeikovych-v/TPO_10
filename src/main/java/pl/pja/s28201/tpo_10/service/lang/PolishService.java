package pl.pja.s28201.tpo_10.service.lang;

import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.dto.ui.AuthUiDto;
import pl.pja.s28201.tpo_10.dto.ui.LandingUiDto;
import pl.pja.s28201.tpo_10.dto.ui.SettingsUiDto;

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

    @Override
    public AuthUiDto getAuthDto() {
        var authDto = new AuthUiDto();

        authDto.setAuthHeader("Autoryzacja");
        authDto.setUrlLabel("Podaj nazwę URL:");
        authDto.setUrlPlaceHolder("Wprowadź URL");
        authDto.setPasswordLabel("Hasło:");
        authDto.setPasswordPlaceHolder("Wprowadź hasło");

        authDto.setButtonSubmit("Zatwierdź");
        authDto.setButtonCancel("Anuluj");
        return authDto;
    }

    @Override
    public SettingsUiDto getSettingsDto() {
        var settingsDto = new SettingsUiDto();

        settingsDto.setSettingsHeader("Autoryzacja");
        settingsDto.setNameLabel("Nazwa URL:");
        settingsDto.setTargetUrlLabel("Docelowy URL:");
        settingsDto.setRedirectUrlLabel("URL przekierowania:");
        settingsDto.setVisitsLabel("Liczba wizyt:");

        settingsDto.setButtonSubmit("Zapisz zmiany");
        settingsDto.setButtonCancel("Wróć");
        settingsDto.setButtonDelete("Usuń");
        return settingsDto;
    }

    @Override
    public String getInvalidPasswordErrorMessage() {
        return "Nieprawidłowe hasło lub nazwa użytkownika.";
    }

    @Override
    public String getLinkCreatedMessage() {
        return "Link został pomyślnie utworzony.";
    }

    @Override
    public String getNotFoundErrorMessage() {
        return "404 Nie znaleziono";
    }

    @Override
    public String getUrlAlreadyExistsErrorMessage() {
        return "Podany URL już istnieje w systemie.";
    }

    @Override
    public String getLinkDeletedMessage() {
        return "Link został pomyślnie usunięty.";
    }
}
