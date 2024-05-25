package pl.pja.s28201.tpo_10.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configureOpenApi() {
        Server server = new Server()
                .url("http://localhost:8080")
                .description("TPO_10");

        Contact contact = new Contact()
                .name("Volodymyr Yeikovych")
                .email("s28201@pjwstk.edu.pl");

        Info info = new Info()
                .title("TPO_10 API")
                .version("1.0.0")
                .description("This is API to complete TPO 10 task.")
                .contact(contact);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
