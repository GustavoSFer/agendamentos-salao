package br.com.fernandes.exceptions;

public class AgendamentoPorClienteException extends RuntimeException {
    public AgendamentoPorClienteException(String msg) {
        super(msg);
    }
}
