package pl.pja.s28201.tpo_10.service.lang;

import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.dto.ui.AuthUiDto;
import pl.pja.s28201.tpo_10.dto.ui.LandingUiDto;
import pl.pja.s28201.tpo_10.dto.ui.SettingsUiDto;
import pl.pja.s28201.tpo_10.model.Language;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class GeneralLanguageService implements ILangService {

    private Language currentAppLanguage = Language.EN;
    private static final Map<Language, ILangService> SERVICE_MAP = new HashMap<>();

    static {
        SERVICE_MAP.put(Language.EN, new EnglishService());
        SERVICE_MAP.put(Language.PL, new PolishService());
        SERVICE_MAP.put(Language.DE, new GermanService());
    }

    public void changeAppLanguage(Language to) {
        currentAppLanguage = to;
        Locale.setDefault(Locale.forLanguageTag(to.name()));
    }

    @Override
    public String getLanguageCode() {
        return SERVICE_MAP.get(currentAppLanguage).getLanguageCode().toLowerCase();
    }

    @Override
    public LandingUiDto getLandingDto() {
        return SERVICE_MAP.get(currentAppLanguage).getLandingDto();
    }

    @Override
    public AuthUiDto getAuthDto() {
        return SERVICE_MAP.get(currentAppLanguage).getAuthDto();
    }

    @Override
    public SettingsUiDto getSettingsDto() {
        return SERVICE_MAP.get(currentAppLanguage).getSettingsDto();
    }

    @Override
    public String getInvalidPasswordErrorMessage() {
        return SERVICE_MAP.get(currentAppLanguage).getInvalidPasswordErrorMessage();
    }

    @Override
    public String getLinkCreatedMessage() {
        return SERVICE_MAP.get(currentAppLanguage).getLinkCreatedMessage();
    }

    @Override
    public String getNotFoundErrorMessage() {
        return SERVICE_MAP.get(currentAppLanguage).getNotFoundErrorMessage();
    }

    @Override
    public String getUrlAlreadyExistsErrorMessage() {
        return SERVICE_MAP.get(currentAppLanguage).getUrlAlreadyExistsErrorMessage();
    }

    @Override
    public String getLinkDeletedMessage() {
        return SERVICE_MAP.get(currentAppLanguage).getLinkDeletedMessage();
    }
}
