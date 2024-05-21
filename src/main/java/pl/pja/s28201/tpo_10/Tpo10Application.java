package pl.pja.s28201.tpo_10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.pja.s28201.tpo_10.repository.UrlObjectRepository;

@SpringBootApplication
@EnableJpaRepositories
public class Tpo10Application {

    public static void main(String[] args) {
        SpringApplication.run(Tpo10Application.class, args);
    }

}
