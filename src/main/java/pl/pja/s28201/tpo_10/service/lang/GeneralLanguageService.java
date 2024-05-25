package pl.pja.s28201.tpo_10.service.lang;

import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.dto.LandingUiDto;
import pl.pja.s28201.tpo_10.model.Language;

import java.util.HashMap;
import java.util.Map;

@Service
public class GeneralLanguageService implements ILangService{

    private Language currentAppLanguage = Language.EN;
    private static final Map<Language, ILangService> SERVICE_MAP = new HashMap<>();

    static {
        SERVICE_MAP.put(Language.EN, new EnglishService());
        SERVICE_MAP.put(Language.PL, new PolishService());
        SERVICE_MAP.put(Language.DE, new GermanService());
    }

    public void changeAppLanguage(Language to) {
        currentAppLanguage = to;
    }

    @Override
    public String getLanguageCode() {
        return SERVICE_MAP.get(currentAppLanguage).getLanguageCode().toLowerCase();
    }

    @Override
    public LandingUiDto getLandingDto() {
        return SERVICE_MAP.get(currentAppLanguage).getLandingDto();
    }
}
