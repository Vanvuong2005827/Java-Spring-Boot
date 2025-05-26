package org.example.btvnbuoi9;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
        info = @Info(
                title = "My Project API",
                version = "1.0.0",
                description = "API dịch vụ của My Project",
                contact = @Contact(name = "Người phát triển", email = "dev@example.com"),
                license = @License(name = "Apache 2.0", url = "http://springdoc.org")
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@SpringBootApplication
public class BtvnBuoi9Application {

    public static void main(String[] args) {
        SpringApplication.run(BtvnBuoi9Application.class, args);
    }

}
