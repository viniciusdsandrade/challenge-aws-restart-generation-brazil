package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Schema(description = "Exceção lançada quando uma matrícula não é encontrada.")
@ResponseStatus(NOT_FOUND)
public class MatriculaNotFoundException extends RuntimeException {
    public MatriculaNotFoundException(String msg) {
        super(msg);
    }
}
