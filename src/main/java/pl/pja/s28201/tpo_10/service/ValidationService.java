package pl.pja.s28201.tpo_10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.service.lang.GeneralLanguageService;

@Service
public class ValidationService {

    private final Environment environment;
    private final GeneralLanguageService languageService;

    @Autowired
    public ValidationService(Environment environment, GeneralLanguageService languageService) {
        this.environment = environment;
        this.languageService = languageService;
    }


    public String getMessageCode(String code) {

        if (!isLocalDomainCode(code)) return code;

        if (isErrorMessage(code)) {
            return getErrorMessageFromCode(code);
        }

        return code;
    }

    private String getErrorMessageFromCode(String code) {
        var codeWithLang = code + "." + languageService.getLanguageCode();
        return environment.getProperty(codeWithLang);
    }

    private boolean isErrorMessage(String code) {
        return code.endsWith("message");
    }

    private boolean isLocalDomainCode(String code) {
        return code.startsWith("s28201");
    }


}
