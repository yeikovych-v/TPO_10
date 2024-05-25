package pl.pja.s28201.tpo_10.config.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pja.s28201.tpo_10.service.ValidationService;

@Configuration
public class MessageConfig {

    private final ValidationService service;

    @Autowired
    public MessageConfig(ValidationService service) {
        this.service = service;
    }

    @Bean
    public MessageSource messageSource() {
        return new RuntimeMessageSource(service);
    }

}
