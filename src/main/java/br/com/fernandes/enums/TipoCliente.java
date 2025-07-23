package br.com.fernandes.enums;

public enum TipoCliente {
    ATIVO("Ativo"),
    BLOQUEADO("Bloqueado"),
    CANCELADO("Cancelado");

    private final String statusCliente;

    TipoCliente(String status){
        this.statusCliente = status;
    }

    public String getStatusCliente() { return this.statusCliente; };
}
