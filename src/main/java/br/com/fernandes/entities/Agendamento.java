package br.com.fernandes.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Agendamento implements Serializable {

    private Long id;
    private Cliente cliente;
    private Servico servico;
    private LocalDateTime dataHora;
    private String observacao;

    public Agendamento() {}

    public Agendamento(Cliente cliente, Servico servico, LocalDateTime dataHora, String observacao) {
        this.cliente = cliente;
        this.servico = servico;
        this.dataHora = dataHora;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
