package br.com.fernandes.exceptions;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public class StartErrorException {

    private String error;
    private Instant instant;
    private HttpStatus status;

    public StartErrorException(String error, Instant instant, HttpStatus status) {
        this.error = error;
        this.instant = instant;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
