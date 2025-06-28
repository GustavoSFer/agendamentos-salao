package br.com.fernandes.exceptions;

public class AgendamentoInvalidoException extends RuntimeException{
    public AgendamentoInvalidoException(String msg) {
        super(msg);
    }
}
