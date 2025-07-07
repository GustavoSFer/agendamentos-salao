package br.com.fernandes.exceptions;

public class ServicoNotFoundException extends RuntimeException {
    public ServicoNotFoundException(String msg) {
        super(msg);
    }
}
