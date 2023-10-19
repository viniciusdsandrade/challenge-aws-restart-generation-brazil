package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .externalDocs(externalDocs());
    }

    @Bean
    public OpenApiCustomizer globalHeaderOpenApiCustomizer() {
        return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
            ApiResponses apiResponses = operation.getResponses();
            addStandardResponses(apiResponses);
        }));
    }

    private Info apiInfo() {
        return new Info()
                .title("Escola API")
                .description("API para gerenciamento de alunos e professores")
                .version("1.0.0")
                .contact(apiContact());
    }

    private Contact apiContact() {
        return new Contact()
                .url("https://github.com/viniciusdsandrade/reStart-aws-challenge")
                .name("Restart")
                .email("vinicius_andrade2010@hotmail.com");
    }

    private ExternalDocumentation externalDocs() {
        return new ExternalDocumentation()
                .description("Documentação da API Escola")
                .url("https://github.com/viniciusdsandrade/reStart-aws-challenge");
    }

    private void addStandardResponses(@NotNull ApiResponses apiResponses) {
        apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
        apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
        apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
        apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
        apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
        apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
        apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
        apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
    }

    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }
}
