package org.accesodatos.hogwarts.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Hogwarts API",
                version = "v1",
                description = "API REST del proyecto Hogwarts"
        )
)
public class OpenApiConfig {
}