package br.com.fernandes.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // Captura erros de validação do corpo (@RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // (Opcional) Captura erros de validação de parâmetros de URL ou query params
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AgendamentoInvalidoException.class)
    public ResponseEntity<StartErrorException> AgendamentoInvalido(AgendamentoInvalidoException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String error = e.getMessage();
        Instant instant = Instant.now();

        StartErrorException iniciandoError = new StartErrorException(error, instant, status);

        return ResponseEntity.status(status).body(iniciandoError);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<StartErrorException> ClienteNotFound (ClienteNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Instant instant = Instant.now();

        StartErrorException erro = new StartErrorException(e.getMessage(), instant, status);

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(ServicoNotFoundException.class)
    public ResponseEntity<StartErrorException> ServicoNotFound(ServicoNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Instant instant = Instant.now();

        StartErrorException erro =  new StartErrorException(e.getMessage(), instant, status);

        return ResponseEntity.status(status).body(erro);
    }
}
