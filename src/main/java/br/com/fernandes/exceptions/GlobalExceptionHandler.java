package br.com.fernandes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AgendamentoInvalidoException.class)
    public ResponseEntity<StartErrorException> AgendamentoInvalido(AgendamentoInvalidoException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = e.getMessage();
        Instant instant = Instant.now();

        StartErrorException iniciandoError = new StartErrorException(error, instant, status);

        return ResponseEntity.status(status).body(iniciandoError);
    }
}
