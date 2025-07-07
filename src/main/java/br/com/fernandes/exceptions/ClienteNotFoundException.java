package br.com.fernandes.exceptions;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String msg) {
        super(msg);
    }
}
