package pl.pja.s28201.tpo_10.service.lang;

import pl.pja.s28201.tpo_10.dto.LandingUiDto;

public interface ILangService {

    String getLanguageCode();

    LandingUiDto getLandingDto();
}
