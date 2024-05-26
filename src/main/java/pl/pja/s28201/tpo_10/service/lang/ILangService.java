package pl.pja.s28201.tpo_10.service.lang;

import pl.pja.s28201.tpo_10.dto.ui.AuthUiDto;
import pl.pja.s28201.tpo_10.dto.ui.LandingUiDto;
import pl.pja.s28201.tpo_10.dto.ui.SettingsUiDto;

public interface ILangService {

    String getLanguageCode();
    LandingUiDto getLandingDto();
    AuthUiDto getAuthDto();
    SettingsUiDto getSettingsDto();
    String getInvalidPasswordErrorMessage();
    String getLinkCreatedMessage();
    String getNotFoundErrorMessage();
    String getUrlAlreadyExistsErrorMessage();
    String getLinkDeletedMessage();
}
