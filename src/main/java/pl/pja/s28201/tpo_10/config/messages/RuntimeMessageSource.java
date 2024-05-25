package pl.pja.s28201.tpo_10.config.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pl.pja.s28201.tpo_10.service.ValidationService;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class RuntimeMessageSource extends AbstractMessageSource {

    private final ValidationService validationService;

    @Autowired
    public RuntimeMessageSource(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    protected MessageFormat resolveCode(@NonNull String code, @NonNull Locale locale) {
        String message = validationService.getMessageCode(code);
        return new MessageFormat(message != null ? message : code, locale);
    }
}
