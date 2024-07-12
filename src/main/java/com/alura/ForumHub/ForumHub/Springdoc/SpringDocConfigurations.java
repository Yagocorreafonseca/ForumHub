package com.alura.ForumHub.ForumHub.Springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ForumHub")
                        .version("1.0")
                        .description("O FórumHub é uma API REST desenvolvida utilizando Spring Boot, simulando o back code de um fórum de discussão. Nesta API, é possível criar, visualizar, atualizar e deletar tópicos de discussão, perfis, respostas, cursos e usuários. Apenas usuários autenticados podem acessar os tópicos. ")
                        .termsOfService("https://github.com/Yagocorreafonseca/ForumHub")
                        .contact(new Contact()
                                .name("Yago Correa Fonseca")
                                .url("https://github.com/Yagocorreafonseca")))

                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

}
