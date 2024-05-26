package pl.pja.s28201.tpo_10.service.lang;

import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.dto.ui.AuthUiDto;
import pl.pja.s28201.tpo_10.dto.ui.LandingUiDto;
import pl.pja.s28201.tpo_10.dto.ui.SettingsUiDto;

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

    @Override
    public AuthUiDto getAuthDto() {
        var authDto = new AuthUiDto();

        authDto.setAuthHeader("Autorisierung");
        authDto.setUrlLabel("URL-Name angeben:");
        authDto.setUrlPlaceHolder("URL eingeben");
        authDto.setPasswordLabel("Passwort:");
        authDto.setPasswordPlaceHolder("Passwort eingeben");

        authDto.setButtonSubmit("Absenden");
        authDto.setButtonCancel("Abbrechen");
        return authDto;
    }

    @Override
    public SettingsUiDto getSettingsDto() {
        var settingsDto = new SettingsUiDto();

        settingsDto.setSettingsHeader("Autorisierung");
        settingsDto.setNameLabel("URL-Name:");
        settingsDto.setTargetUrlLabel("Ziel-URL:");
        settingsDto.setRedirectUrlLabel("Weiterleitungs-URL:");
        settingsDto.setVisitsLabel("Anzahl der Besuche:");

        settingsDto.setButtonSubmit("Änderungen speichern");
        settingsDto.setButtonCancel("Abbrechen und zurück");
        settingsDto.setButtonDelete("Datensatz löschen");
        return settingsDto;
    }

    @Override
    public String getInvalidPasswordErrorMessage() {
        return "Ungültiges Passwort oder Benutzername.";
    }

    @Override
    public String getLinkCreatedMessage() {
        return "Link wurde erfolgreich erstellt.";
    }

    @Override
    public String getNotFoundErrorMessage() {
        return "404 Nicht gefunden";
    }

    @Override
    public String getUrlAlreadyExistsErrorMessage() {
        return "Die angegebene URL existiert bereits im System.";
    }

    @Override
    public String getLinkDeletedMessage() {
        return "Link wurde erfolgreich gelöscht.";
    }
}
