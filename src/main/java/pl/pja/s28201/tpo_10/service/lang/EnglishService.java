package pl.pja.s28201.tpo_10.service.lang;

import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.dto.ui.AuthUiDto;
import pl.pja.s28201.tpo_10.dto.ui.LandingUiDto;
import pl.pja.s28201.tpo_10.dto.ui.SettingsUiDto;

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

    @Override
    public AuthUiDto getAuthDto() {
        var authDto = new AuthUiDto();

        authDto.setAuthHeader("Authorization");
        authDto.setUrlLabel("Provide URL Name:");
        authDto.setUrlPlaceHolder("Enter URL");
        authDto.setPasswordLabel("Password:");
        authDto.setPasswordPlaceHolder("Enter Password");

        authDto.setButtonSubmit("Submit");
        authDto.setButtonCancel("Cancel");
        return authDto;
    }

    @Override
    public SettingsUiDto getSettingsDto() {
        var settingsDto = new SettingsUiDto();

        settingsDto.setSettingsHeader("Authorization");
        settingsDto.setNameLabel("URL Name:");
        settingsDto.setTargetUrlLabel("Target URL:");
        settingsDto.setRedirectUrlLabel("Redirect URL:");
        settingsDto.setVisitsLabel("Number of Visits:");

        settingsDto.setButtonSubmit("Save Changes");
        settingsDto.setButtonCancel("Cancel and Go Back");
        settingsDto.setButtonDelete("Delete Record");
        return settingsDto;
    }


    @Override
    public String getInvalidPasswordErrorMessage() {
        return "Invalid password or username.";
    }

    @Override
    public String getLinkCreatedMessage() {
        return "Link was Successfully created.";
    }

    @Override
    public String getNotFoundErrorMessage() {
        return "404 Not Found";
    }

    @Override
    public String getUrlAlreadyExistsErrorMessage() {
        return "Provided Url already exists in the system.";
    }

    @Override
    public String getLinkDeletedMessage() {
        return "Link was deleted Successfully.";
    }
}
