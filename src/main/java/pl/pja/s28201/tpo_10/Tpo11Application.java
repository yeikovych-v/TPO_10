package pl.pja.s28201.tpo_10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Tpo11Application {

    public static void main(String[] args) {
        SpringApplication.run(Tpo11Application.class, args);
    }

}
